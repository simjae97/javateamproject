package model.dto;

public class purchasereportDTO extends ReportDTO{
    String itemlist;
    int totalprice;

    public purchasereportDTO(String reporttitle, String reportcontent, String itemlist, int totalprice) {
        super(reporttitle, reportcontent);
        this.itemlist = itemlist;
        this.totalprice = totalprice;
    }

    public String getItemlist() {
        return itemlist;
    }

    public void setItemlist(String itemlist) {
        this.itemlist = itemlist;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }



    @Override
    public String toString() {
        return super.toString() +
                "itemlist='" + itemlist + '\'' +
                ", totalprice=" + totalprice + "}"
               ;
    }
}
