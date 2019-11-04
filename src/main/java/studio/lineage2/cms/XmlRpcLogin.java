package studio.lineage2.cms;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.lineage2.cms.model.*;
import studio.lineage2.cms.service.GAccountService;
import studio.lineage2.cms.service.ServerService;
import studio.lineage2.cms.utils.Rnd;
import studio.lineage2.cms.utils.XmlRpcUtil;

import java.util.List;

@Controller
@RequestMapping("/XmlRpcLogin")
@RequiredArgsConstructor
public class XmlRpcLogin implements ILogin {
  private final ServerService serverService;
  private final GAccountService gAccountService;


  @Override
  @RequestMapping(value = "/change", method = {RequestMethod.POST})
  @ResponseBody
  public IMessage change(long serverId, String gl, String gp) {
    Server server = serverService.findOne(serverId);

    if (server == null || server.getType() != ServerType.LOGIN || !server.isEnable()) {
      return new IMessage(IMessage.Type.FAIL, "Что-то пошло не так");
    }

    if (!gp.matches("[A-Za-z0-9]{4,16}")) {
      return new IMessage(IMessage.Type.FAIL, "Пароль должен содержать 4-16 символов (A-Za-z0-9)");
    }

    MAccount mAccount = (MAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    List<GAccount> gAccounts = gAccountService.findByMAccountIdAndServerId(mAccount.getId(), serverId);
    if (gAccounts.isEmpty()) {
      return new IMessage(IMessage.Type.FAIL, "Что-то пошло не так");
    }

    for (GAccount gAccount : gAccounts) {
      if (gAccount.getName().equals(gl)) {
        return XmlRpcUtil.getMessage(server, "XmlRpcLogin.change", gl.toLowerCase(), gp);
      }
    }

    return new IMessage(IMessage.Type.FAIL, "Что-то пошло не так");
  }

  @Override
  @RequestMapping(value = "/reg", method = {RequestMethod.POST})
  @ResponseBody
  public IMessage reg(long serverId, String gl, String gp) {
    Server server = serverService.findOne(serverId);

    if (server == null || server.getType() != ServerType.LOGIN || !server.isEnable()) {
      return new IMessage(IMessage.Type.FAIL, "Что-то пошло не так");
    }

    if (!gl.matches("[A-Za-z0-9]{4,11}")) {
      return new IMessage(IMessage.Type.FAIL, "Логин должен содержать 4-11 символов (A-Za-z0-9)");
    }

    if (!gp.matches("[A-Za-z0-9]{4,16}")) {
      return new IMessage(IMessage.Type.FAIL, "Пароль должен содержать 4-16 символов (A-Za-z0-9)");
    }

    gl = Rnd.getPrefix() + gl;

    IMessage message = XmlRpcUtil.getMessage(server, "XmlRpcLogin.reg", gl.toLowerCase(), gp);

    if (message.getType() == IMessage.Type.SUCCESS) {
      MAccount ownerId = (MAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      gAccountService.save(new GAccount(ownerId.getId(), server.getId(), gl));
    }

    return message;
  }
}
