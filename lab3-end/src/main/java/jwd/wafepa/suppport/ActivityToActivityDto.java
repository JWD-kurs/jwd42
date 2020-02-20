package jwd.wafepa.suppport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Activity;
import jwd.wafepa.web.dto.ActivityDTO;

@Component
public class ActivityToActivityDto implements Converter<Activity, ActivityDTO>{

	@Override
	public ActivityDTO convert(Activity activity) {
		ActivityDTO dto = new ActivityDTO();
		
		dto.setId(activity.getId());
		dto.setName(activity.getName());
		
		return dto;
	}

	public List<ActivityDTO> convert(List<Activity> activities){
		List<ActivityDTO> ret = new ArrayList<>();
		
		for(Activity a : activities) {
			ActivityDTO dto = convert(a);
			ret.add(dto);
		}
		
		return ret;
	}
}
