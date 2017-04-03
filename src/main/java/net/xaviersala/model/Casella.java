package net.xaviersala.model;

public class Casella {

  String contingut;

  public Casella(String c) {
    contingut = c;
  }

  /**
   * @return the contingut
   */
  public String getContingut() {
    return contingut;
  }

  /**
   * @param contingut the contingut to set
   */
  public void setContingut(String contingut) {
    this.contingut = contingut;
  }

  @Override
  public String toString() {
    return contingut;
  }

}
