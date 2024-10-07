package org.zerock.myapp.sample;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import lombok.Data;


@Data

@Component("restaurant")
public class Restaurant {
	
//	@Setter(onMethod_= { @Autowired })
//	@Setter(onMethod_= { @Inject })
//	@Setter(onMethod_= { @Resource })
//	@Setter(onMethod_= { @Resource(type=Chef.class) })
	
//	@Setter(onMethod_= @Autowired)
//	@Setter(onMethod_= @Inject)
//	@Setter(onMethod_= @Resource)
//	@Setter(onMethod_= @Resource(type=Chef.class))
	
//	@Autwired
//	@Inject
//	@Resource
	@Resource(type=Chef.class)
	private Chef chef;
	
	

} // end class
