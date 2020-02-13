package jwd.wafepa.service;

import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;

import jwd.wafepa.model.Activity;
import jwd.wafepa.service.impl.InMemoryActivityService;

public class InMemoryActivityServiceTest {

	private ActivityService activityService;
	
	@Before
	public void setUp() {
		activityService = new InMemoryActivityService();
		
		Activity a1 = new Activity("Swimming");
		Activity a2 = new Activity("Running");
		
		activityService.save(a1);
		activityService.save(a2);
		
		//activityService.save(new Activity("Football"));
	}
	
	@Test
	public void testFindOne() {
		Activity a = activityService.findOne(2L);
		
		Assert.assertEquals("Running", a.getName());
	}
	
	@Test
	public void testFindOneNonexisting() {
		Activity a = activityService.findOne(3L);
		
		Assert.assertNull(a);
	}
	
	@Test(/*expected = InvalidAttributesException.class*/)
	public void testFindOneInvalid() {
		Activity a = activityService.findOne(null);
		
		Assert.assertNull(a);
	}
	
	@Test
	public void testFindAll() {
		List<Activity> result = activityService.findAll();
		
		Assert.assertEquals(2, result.size());
	}
}
