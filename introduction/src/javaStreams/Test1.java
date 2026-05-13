package javaStreams;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.Assert;
import org.testng.annotations.Test;


public class Test1 {
	//count the number of names starting with A in the list
	
	//@Test
	public void regular() {
		ArrayList<String> names=new ArrayList<String>();
		names.add("Sam");
		names.add("Aron");
		names.add("Adam");
		names.add("Ram");
		names.add("Jack");
		int count=0;
		
		for(int i=0;i<names.size();i++)
		{
			String actual=names.get(i);
			if(actual.startsWith("A"))
			{
				count++;
			}
		}
		
		System.out.println(count);
	}
	
	
	//@Test
	private void streamFilter() {
		// TODO Auto-generated method stub
		ArrayList<String> names=new ArrayList<String>();
		names.add("Sam");
		names.add("Aron");
		names.add("Adam");
		names.add("Ram");
		names.add("Samuel");
		long count;
		
		// stream API and filtering
		count=names.stream()
				.filter(s->s.startsWith("A"))
				.count();
		System.out.println(count);
		
		
		//create lightweight stream and use filter directly 
		long count1=Stream.of("Sam","Adam","Aron","Ram","Samuel")
				.filter(s->s.startsWith("A"))
				.count();
		
		System.out.println(count1);
		
		//print all names of ArrayList which have name length>3
		names.stream()
		.filter(s->s.length()>3)
		.forEach(s->System.out.println(s));	// use filter to get new stream after applying condition. use forEach to go through each and every object of new stream and print it
		
		
		names.stream()
		.filter(s->s.length()>3)
		.limit(1)
		.forEach(s->System.out.println(s));
		
		

	}
	
	//@Test
	public void streamMap() {
		
		// print names which have last letter "a" with upper case
		Stream.of("Sam","Rama","Aron","Adama","Samuel").
		filter(s->s.endsWith("a"))
		.map(s->s.toUpperCase())
		.forEach(s->System.out.println(s));
		
		
		// print names which have first letter "A" with upper case and sorted
		List<String> names1= Arrays.asList("Sam",	"Rama","Aron","Adama","Samuel");
		names1.stream()
		.filter(s->s.startsWith("A"))
		.map(s->s.toUpperCase())
		.sorted()
		.forEach(s->System.out.println(s));
		
		ArrayList<String> names=new ArrayList<String>();
		names.add("Don");
		names.add("Jack");
		names.add("Carl");
		
		
		//merge 2 diff lists . convert to stream and concatenate
		Stream<String> mergedStream= Stream.concat(names.stream(), names1.stream());
		System.out.println("concatenated stream");
		//mergedStream.forEach(s->System.out.println(s));
		
		//check match
		boolean flag= mergedStream.anyMatch(s->s.equalsIgnoreCase("Adama"));
		Assert.assertTrue(flag);
		
	}
	
	@Test
	public void streamCollect() {
		
		List<String> newList= Stream.of("Sam","Rama","Aron","Adama","Samuel")
				.filter(s->s.endsWith("a"))
				.map(s->s.toUpperCase())
				.collect(Collectors.toList());
		
		System.out.println(newList.get(0));
		
		
		//get unique number and sort array
		List<Integer> numberList= Arrays.asList(3,2,2,7,5,1,9);
		numberList.stream().distinct().sorted().forEach(s->System.out.println(s));
		
		
		//get unique number, sort array and get 3rd number
		List<Integer> numberList1= numberList.stream().distinct().sorted().collect(Collectors.toList());
		System.out.println(numberList1.get(2));

	}
	
}
