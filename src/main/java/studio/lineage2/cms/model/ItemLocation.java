package studio.lineage2.cms.model;

public enum ItemLocation {
  VOID,
  INVENTORY,
  PAPERDOLL,
  PET_INVENTORY,
  PET_PAPERDOLL,
  WAREHOUSE,
  CLANWH,
  FREIGHT,
  /**
   * @deprecated
   */
  @Deprecated
  LEASE,
  MAIL;
}
