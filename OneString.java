public class OneString {

    private String str;
    private Boolean del;

    public OneString(){
        str = null;
        del = false;
    }

    public void add(String newStr){
        str = newStr;
        del = false;
    }

    public String getString(){
        return str;
    }

    public Boolean stopSearch(){
        if ((isEmpty()) && (!del))
            return true;
        return false;
    }

    public Boolean equals(String val){
        if (isEmpty())
            return false;
        if (str.equals(val))
            return true;
        return false;
    }

    public void delete(){
        str = null;
        del = true;
    }

    public Boolean isEmpty(){
        if (str == null)
            return true;
        return false;
    }

}
