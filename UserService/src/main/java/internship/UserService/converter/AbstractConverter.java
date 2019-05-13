package internship.UserService.converter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * This class represents abstract class for converting entities.
 * @author s.krasic
 *
 */

public abstract class AbstractConverter {
	
	/**
	 * This method is method to convert original entity to DTO entity.
	 * @return DTO entity.
	 */

	public static <D, E> List<D> fromEntityList(List<E> entityList, Function<E, D> e) {
		return entityList.stream().map(e).collect(Collectors.toList());
	}
	
	/**
	 * This method is method to convert DTO entity to original entity.
	 * @return original entity.
	 */

	public static <D, E> List<E> toEntityList(List<D> dtoList, Function<D, E> e) {
		return dtoList.stream().map(e).collect(Collectors.toList());
	}
	
}
