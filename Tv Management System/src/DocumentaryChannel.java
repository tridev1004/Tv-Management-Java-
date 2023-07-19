public class DocumentaryChannel extends TvChannel{
    int additionalfee=12;
    public DocumentaryChannel( String channelName, String language, String category,int price) {
        super( channelName, language, category,price);
    }
    @Override
    public int getPrice() {
        return super.getPrice()+additionalfee;
    }
}
