package studio.lineage2.cms.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import studio.lineage2.cms.model.IMessage;
import studio.lineage2.cms.model.MAccount;
import studio.lineage2.cms.service.ItemService;
import studio.lineage2.cms.service.MAccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Eanseen
 * 16.06.2016
 */
@Controller
@RequestMapping("/pay")
public class PayController {
  @Value("${unitpayMerchantId}")
  private int unitpayMerchantId;
  @Value("${unitpaySecretKey1}")
  private String unitpaySecretKey1;
  @Value("${unitpaySecretKey2}")
  private String unitpaySecretKey2;
  @Value("${unitpayDesc}")
  private String unitpayDesc;

  @Autowired
  private MAccountService mAccountService;
  @Autowired
  private ItemService itemService;

  public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map) {
    List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
    list.sort(Comparator.comparing(Map.Entry::getKey));

    Map<K, V> result = new LinkedHashMap<>();
    for (Map.Entry<K, V> entry : list) {
      result.put(entry.getKey(), entry.getValue());
    }

    return result;
  }

  @RequestMapping(value = "/init/{sum}", method = {RequestMethod.GET})
  public
  @ResponseBody
  IMessage init(@PathVariable int sum) {
    if (sum < 1) {
      return new IMessage(IMessage.Type.SUCCESS, "/cp");
    }

    MAccount mAccount = (MAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    StringBuilder sb = new StringBuilder();
    sb.append(unitpaySecretKey1);
    sb.append("?sum=").append(sum);
    sb.append("&account=").append(mAccount.getUsername());
    sb.append("&desc=").append(unitpayDesc);
    sb.append("&currency=").append("RUB");
    String signature = mAccount.getUsername() +
      "{up}" + "RUB" +
      "{up}" + unitpayDesc +
      "{up}" + sum +
      "{up}" + unitpaySecretKey2;
    sb.append("&signature=").append(DigestUtils.sha256Hex(signature));

    return new IMessage(IMessage.Type.SUCCESS, "https://unitpay.ru/pay/" + sb.toString());
  }

  @RequestMapping(value = "/checkunitpay", method = {RequestMethod.GET})
  public
  @ResponseBody
  String checkunitpay(HttpServletRequest request) {
    Map<String, String[]> params = request.getParameterMap();

    String method = params.get("method")[0];

    StringBuilder signature = new StringBuilder(method + "{up}");

    for (Map.Entry<String, String[]> param : sortByKey(params).entrySet()) {
      if (param.getKey().equalsIgnoreCase("method") || param.getKey().equalsIgnoreCase("params[sign]") || param.getKey().equalsIgnoreCase("params[signature]")) {
        continue;
      }
      signature.append(param.getValue()[0]).append("{up}");
    }
    signature.append(unitpaySecretKey2);
    signature = new StringBuilder(DigestUtils.sha256Hex(signature.toString()));

    if (!signature.toString().equals(params.get("params[signature]")[0])) {
      return "{\"error\": {\"message\": \"Ошибка\"}}";
    }

    if (method.equals("pay")) {
      int sum = Integer.parseInt(params.get("params[sum]")[0]);
      MAccount mAccount = mAccountService.findByUsername(params.get("params[account]")[0]);
      itemService.addUserItem(mAccount.getId(), 100000, sum, "Пополнение баланса");
    }

    return "{\"result\": {\"message\": \"Запрос успешно обработан\"}}";
  }
}
