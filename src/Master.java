class Master {
  ltsource ltsourceObj;

  public Master() {
    ltsourceObj = new ltsource();

    System.out.println(ltsourceObj.bar(99));
  }

  public static void main(String... args) {
    Master masterObj = new Master();
  }
}
