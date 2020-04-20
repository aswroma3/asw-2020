package asw.efood.restaurantservice.client.domain;

import lombok.*; 

@Data 
@NoArgsConstructor @AllArgsConstructor
public class Restaurant {

	private Long id; 
	private String name; 
	private String location; 
	
}
