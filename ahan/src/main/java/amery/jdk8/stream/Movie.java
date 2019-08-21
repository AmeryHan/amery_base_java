package amery.jdk8.stream;

import com.google.common.base.Objects;

/**
 * Created by ahan on 23/12/2016.
 */
public class Movie {

    private Integer rank;
    private String description;

    public Movie(Integer rank, String description) {
        super();
        this.rank = rank;
        this.description = description;
    }

    public Integer getRank() {
        return rank;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("rank", rank)
                .add("description", description)
                .toString();
    }
}
