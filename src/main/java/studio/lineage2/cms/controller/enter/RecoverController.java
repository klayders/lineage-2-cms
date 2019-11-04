package studio.lineage2.cms.controller.enter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import studio.lineage2.cms.model.MAccount;
import studio.lineage2.cms.other.ValidationResult;
import studio.lineage2.cms.service.MAccountService;
import studio.lineage2.cms.service.ReCaptchaService;
import studio.lineage2.cms.utils.MailUtil;
import studio.lineage2.cms.utils.Rnd;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/enter/recover")
@RequiredArgsConstructor
public class RecoverController {
  private MailUtil mailUtil;
  private MAccountService mAccountService;
  private ReCaptchaService reCaptchaService;
  private HttpServletRequest request;


  @RequestMapping(method = {RequestMethod.GET})
  public String index(ModelMap model) {
    model.addAttribute("mAccount", new MAccount());
    model.addAttribute("page", "cp/enter/recover.vm");
    return "cp/index";
  }

  @RequestMapping(method = {RequestMethod.POST})
  public String recover(ModelMap model, @ModelAttribute(value = "mAccount") @Valid MAccount mAccount, BindingResult result) {
    ValidationResult validationResult = reCaptchaService.check(request.getParameter("g-recaptcha-response"), request.getRemoteAddr());
    if (!validationResult.isSuccess()) {
      model.addAttribute("recaptchaError", "Пройдите проверку");
      model.addAttribute("mAccount", mAccount);
      model.addAttribute("page", "cp/enter/recover.vm");
      return "cp/index";
    }

    if (result.hasErrors()) {
      model.addAttribute("page", "cp/enter/recover.vm");
      return "cp/index";
    }

    if (!mAccountService.containsByUsername(mAccount.getUsername())) {
      result.rejectValue("username", "mAccount.username", "Пользователь не существует");
      model.addAttribute("page", "cp/enter/recover.vm");
      return "cp/index";
    }

    if (mailUtil.isEnabled()) {
      String password = Rnd.getPassword();

      MAccount temp = mAccountService.findByUsername(mAccount.getUsername());
      temp.setPassword(new BCryptPasswordEncoder().encode(password));
      mAccountService.save(temp);

      String sb = "Вы восстановили пароль на проекте" + " " + mailUtil.getTitle() + "<br><br>" +
        "<b>" + "Ваш Email" + ":</b> " + mAccount.getUsername() + "<br>" +
        "<b>" + "Ваш Пароль" + ":</b> " + password + "<br>";
      mailUtil.send(temp.getUsername(), mailUtil.getTitle() + " - Восстановление пароля", sb);

      model.addAttribute("message", "Восстановление пароля прошло успешно, информация отправлена на Email");
    } else {
      model.addAttribute("message", "Восстановление пароля отключено");
    }

    model.addAttribute("page", "cp/message.vm");
    return "cp/index";
  }
}
