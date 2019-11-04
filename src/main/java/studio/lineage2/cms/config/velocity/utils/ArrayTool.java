package studio.lineage2.cms.config.velocity.utils;

public class ArrayTool {
  public String arrayItem(String str, String delimiter, int index) {
    if (str == null || str.equals("")) {
      return str;
    }
    String[] array = str.split(delimiter);
    if (array.length < index + 1) {
      return null;
    }
    return array[index];
  }

  public int arrayLength(String str, String delimiter) {
    if (str == null || str.equals("")) {
      return 0;
    }
    String[] array = str.split(delimiter);
    return array.length;
  }

  public int arrayLength(Object[] array) {
    if (array != null) {
      return array.length;
    } else {
      return -1;
    }
  }
}
