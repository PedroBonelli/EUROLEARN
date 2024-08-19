package com.eurolearn.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ReflectionUtils;

public class EntityCopyable {
	
	private class PropertyWithValue{
		public Object value;
		public String propertyName;
		
		public PropertyWithValue(Object value, String propertyName) {
			super();
			this.value = value;
			this.propertyName = propertyName;
		}
		
	}
	
	public void copyDtoToEntity(Object dto, Object entity) {
		
		List<PropertyWithValue> dtoPropertyValues = new ArrayList<PropertyWithValue>();	
		
		ReflectionUtils.doWithFields(dto.getClass(), (field) -> {
			dtoPropertyValues.add(new PropertyWithValue(field.get(dto), field.getName()));
		});
		
		ReflectionUtils.doWithFields(entity.getClass(), (field) -> {
			for(PropertyWithValue pair : dtoPropertyValues) {
				if(field.getName().equalsIgnoreCase(pair.propertyName)) {
					field.set(entity, pair.value);
				}
			}
		});
		
	}
	
}
