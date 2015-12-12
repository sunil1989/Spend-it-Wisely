package com.spend.wisely.db;

/**
 * Created by Sunil on 12/1/2015.
 */
public class Apps {

    String packageName;
    String useTime;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Apps()

    {

        this.packageName = "";
        this.useTime = "";
    }

    /*  public Apps(String packageName,String useTime)
      {

          this.packageName = packageName;
          this.useTime = useTime;
      }*/
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }
}
