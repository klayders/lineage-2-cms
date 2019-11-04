package studio.lineage2.cms.config.velocity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AutocompleteVelocityHelper {

  private static final String DEFAULT_LABEL = "undefined";

//  /**
//   * Find reference
//   *
//   * @param ref       ClassName:entityId
//   * @param fieldName some field
//   * @return field data
//   */
//  public String getAutocompleteData(String ref, String fieldName) throws Exception {
//    if (StringUtils.isEmpty(ref) || StringUtils.isEmpty(fieldName)) {
//      log.warn("getAutocompleteData: ref or fieldName are empty");
//      return DEFAULT_LABEL;
//    }
//    String[] refData = ref.split(":");
//    GenericDAO<?> genericDAO = repository.getByName(refData[0]);
//    if (genericDAO == null) {
//      log.warn("getAutocompleteData: can't find genericDao by ref={}", ref);
//      return DEFAULT_LABEL;
//    }
//
//    Object entity = genericDAO.get(UUID.fromString(refData[1]));
//    // TODO: add check roles for read
//    Field field = entity.getClass().getDeclaredField(fieldName);
//    field.setAccessible(true);
//
//    return String.valueOf(field.get(entity));
//  }

}
