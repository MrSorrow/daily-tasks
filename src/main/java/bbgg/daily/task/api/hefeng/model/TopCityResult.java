package bbgg.daily.task.api.hefeng.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TopCityResult extends Result {

    private List<City> topCityList;
}