package studio.lineage2.cms.config.velocity.utils;

//import pulseteam.portal.managed.dao.PeriodManagedEntity;
//import pulseteam.portal.utils.NumberHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

@SuppressWarnings("unchecked")
public class ObjectTool {
  public Object getProperty(Object instance, String fieldName) {
    if (fieldName == null) {
      return null;
    }
    String[] fields = fieldName.split("\\.");
    Object result = instance;
    for (String field : fields) {
      if (result == null)
        continue;
      result = getFieldValue(result, field);
      if (result == null)
        return null;
    }
    return result;
  }

  public boolean isBoolean(Object object) {
    Class<?> clazz = object.getClass();
    return clazz.equals(boolean.class) || clazz.equals(Boolean.class);
  }

  public boolean isCollection(Object object) {
    return object instanceof Collection;
  }

  public boolean isString(Object object) {
    return object instanceof String;
  }

  public List asList(Object[] array) {
    return Arrays.asList(array);
  }

//  public boolean isPeriodManagedEntity(Object object) {
//    return object instanceof PeriodManagedEntity;
//  }

  public List stringAsList(String argv, String reExp) {
    if (reExp.equals("."))
      reExp = "\\.";
    return Arrays.asList(argv.split(reExp));
  }

  protected Object getFieldValue(Object instance, String fieldName) {
    Class<?> clazz = instance.getClass();
    Field field = getField(clazz, fieldName);
    if (field != null) {
      try {
        return field.get(instance);
      } catch (Exception e) {
        return null;
      }
    }
    Method method = findReadMethod(clazz, fieldName);
    if (method != null) {
      try {
        return method.invoke(instance);
      } catch (Exception e) {
      }
    }
    return null;
  }

  protected Method findReadMethod(Class<?> clazz, String fieldName) {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append((fieldName.charAt(0) + "").toUpperCase());
    stringBuffer.append(fieldName.substring(1));
    String nameToUse = stringBuffer.toString();
    Method method = getMethod(clazz, "get" + nameToUse);
    if (method == null) {
      method = getMethod(clazz, "is" + nameToUse);
    }
    return method;
  }

  protected Field getField(Class<?> clazz, String fieldName) {
    try {
      return clazz.getField(fieldName);
    } catch (Exception e) {
      return null;
    }
  }

  protected Method getMethod(Class<?> clazz, String methodName) {
    try {
      return clazz.getMethod(methodName);
    } catch (Exception e) {
      return null;
    }
  }

  public float round(float value) {
    return (((float) Math.round(value * 10)) / 10);
  }

  public float summ(float value1, float value2) {
    return (value1 + value2);
  }

  public long summ(long value1, long value2) {
    return (value1 + value2);
  }

  public int toInt(float value) {
    if ((value <= -0.1F) && (value >= -0.9F))
      return -1;
    else if ((value >= 0.1) && (value <= 0.9))
      return 1;
    else
      return (int) value;
  }

  public boolean longCompare(long value1, long value2) {
    return value1 == value2;
  }

//  public int praseInt(String str) {
//    return NumberHelper.parseInt(str, 0);
//  }

  public int longToInt(Object o) {
    String className = o.getClass().getSimpleName();
    if (className.equals("Long")) {
      Long value = (Long) o;
      return value.intValue();
    }
    return -1;
  }

  public String removeDot(String str) {
    return str.replaceAll("\\.", "_");
  }

  public String replaceEnters(String str) {
    return str.replaceAll("\\n", "<br/>");
  }

  public ArrayList<Integer> getLoopPages(Object leftIncr, Object rightInr) {
    if (leftIncr == null || rightInr == null)
      return null;
    String fromClassName = leftIncr.getClass().getSimpleName();
    String toClassName = rightInr.getClass().getSimpleName();
    int from;
    int to;
    if (fromClassName.equals("Integer"))
      from = ((Integer) leftIncr).intValue();
    else
      from = ((Long) leftIncr).intValue();
    if (toClassName.equals("Integer"))
      to = ((Integer) rightInr).intValue();
    else
      to = ((Long) rightInr).intValue();
    ArrayList<Integer> loopPages = new ArrayList<Integer>();
    for (int i = from; i < to + 1; i++) {
      loopPages.add(new Integer(i));
    }
    return loopPages;
  }

  public String toCapitalString(String str) {
    String result = null;
    if (str != null && str.length() > 0) {
      result = str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
    }
    return result;
  }

  public String moneyFormat(long value) {
    DecimalFormat myFormatter = new DecimalFormat("$###,###");
    return myFormatter.format(value);
  }

  public String getCharacterRus(Object i) {
    Integer value = (Integer) i;
    return String.valueOf((char) (value.intValue() + 1072));
  }

  public String getCharacterEn(Object i) {
    Integer value = (Integer) i;
    return String.valueOf((char) (value.intValue() + 97));
  }

  public String getClassName(Object e) {
    return e.getClass().getSimpleName();
  }

  public int compareDates(Date first, Date second) {
    Calendar firstCalendar = Calendar.getInstance();
    firstCalendar.setTime(first);
    Calendar secondCalendar = Calendar.getInstance();
    secondCalendar.setTime(second);
    return firstCalendar.compareTo(secondCalendar);
  }

  public boolean datesEquals(Date first, Date second) {
    Calendar firstCalendar = Calendar.getInstance();
    firstCalendar.setTime(first);
    Calendar secondCalendar = Calendar.getInstance();
    secondCalendar.setTime(second);
    return ((firstCalendar.get(Calendar.YEAR) == secondCalendar.get(Calendar.YEAR)) && (firstCalendar.get(Calendar.MONTH) == secondCalendar.get(Calendar.MONTH)) && (firstCalendar
      .get(Calendar.DATE) == secondCalendar.get(Calendar.DATE)));
  }

  public long toLong(BigInteger integer) {
    return (long) integer.intValue();
  }

  public Calendar getCalendar() {
    return Calendar.getInstance();
  }
}
