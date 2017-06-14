package report;

import java.util.List;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class TransactionReport {
    private List<String> contents;

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionReport that = (TransactionReport) o;

        return contents != null ? contents.equals(that.contents) : that.contents == null;
    }

    @Override
    public int hashCode() {
        return contents != null ? contents.hashCode() : 0;
    }

    @Override
    public String toString() {
        String result = "";
        for(String content : contents){
            result+=content+"\n";
        }
        return result.trim();
    }
}
