package sl.ms.inventorymanagement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import sl.ms.inventorymanagement.config.Person;

@Service
public class Practice {

	public List<String> findDuplicates() {
		List<String> str = new ArrayList<>();

		str.add("Hi");
		str.add("test");
		str.add("Hi");
		List<String> str1 = Arrays.asList(new String[] { "Hello", "Sample", "Pract", "Hello" });
		
		Map<String, Long> map = str1.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		//System.out.println(map);

		List<Person> person = getPersons();

		Map<String, List<Person>> map1 = person.stream().collect(Collectors.groupingBy(Person::getLast));// ,Collectors.counting()
		System.out.println(map1);

		List<Person> dupPersons = person.stream().collect(Collectors.groupingBy(Person::getLast)).values().stream()
				.filter(x -> x.size() > 1).flatMap(Collection::stream).collect(Collectors.toList());

		List<Person> dupPersons1 = person.stream()
				.collect(Collectors.groupingBy(p1 -> Arrays.asList(p1.getFirst(), p1.getLast()))).values().stream() // arrays.aslist
																													// will
				// be more accurate
				// ).values().stream()
				.filter(x -> x.size() > 1).flatMap(Collection::stream).collect(Collectors.toList());

		List<Person> dupPersons2 = person.stream().collect(Collectors.groupingBy(p1 -> p1.getFirst() + p1.getLast()))
				.values().stream().filter(x -> x.size() > 1).flatMap(Collection::stream).collect(Collectors.toList());
		//System.out.println(dupPersons);
		
		person.stream().min(Comparator.comparing(Person::getId));
		
		person.stream().mapToInt(x->x.getId()).summaryStatistics().getAverage();
		
		Arrays.asList(1,2,3,4).stream().mapToInt(x->x).reduce(0,Integer::sum);
		
		//sorts the existing list
		person.sort(Comparator.comparing(Person::getFirst));
		//gives new stream
		person.stream().sorted(Comparator.comparing(Person::getFirst).thenComparing(Comparator.comparing(Person::getLast)).reversed());
		//char[] c = "abcmnmnkjhkjhabclkjh".toCharArray();
		
		Map<Character,Long> map2="abcmnmnkjhkjhabclkjh".chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		//System.out.println(map2);
		map2.entrySet().stream().filter(a->a.getValue()>1).map(x->x.getValue()).toArray(Character[]::new);
		//find distinct
		Character[] cc="abcmnmnkjhkjhabclkjh".chars().mapToObj(x->(char)x).distinct().toArray(Character[]::new);
		//find distinct by
		Map<String,Person> per=person.stream().collect(Collectors.toMap(Person::getFirst, Function.identity(),(p1,p2)->p1));
		//Function<Person,String> f=t->t.getFirst();
		List<Person> distinctPerson=person.stream().filter(distinctByKey(t->t.getFirst())).collect(Collectors.toList());
		

		System.out.println(distinctPerson);
		
		return str1;
	}
	
	
	@SafeVarargs
	private static <T> Predicate<T> distinctByKey( Function<? super T,String>... key){
		
		Map<Object,Boolean> map=new HashMap<>();
		return t -> {
			final  List<?> keys= Arrays.stream(key).map(k->k.apply(t)).collect(Collectors.toList());
			
			return map.putIfAbsent(keys, Boolean.TRUE)==null;
		};
		
		//return t->map.putIfAbsent(key.apply(t),Boolean.TRUE)==null;
	}

	private static List<Person> getPersons() {
		List<Person> person = new ArrayList<>();
		Person p = new Person();
		p.setFirst("first");
		p.setLast("last");
		p.setTotalAmount(Double.valueOf(12));

		person.add(p);
		p = new Person();
		p.setFirst("first1");
		p.setLast("last1");
		p.setTotalAmount(Double.valueOf(12));
		person.add(p);

		p = new Person();
		p.setFirst("first2");
		p.setLast("last2");
		p.setTotalAmount(Double.valueOf(12));
		person.add(p);

		p = new Person();
		p.setFirst("first");
		p.setLast("last");
		p.setTotalAmount(Double.valueOf(12));
		person.add(p);

		return person;
	}
}
