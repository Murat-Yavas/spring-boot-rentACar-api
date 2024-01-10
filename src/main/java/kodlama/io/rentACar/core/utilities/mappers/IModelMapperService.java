package kodlama.io.rentACar.core.utilities.mappers;

import org.modelmapper.ModelMapper;

public interface IModelMapperService {
	ModelMapper forResponse(); //response nesneleri için ModelMapper üretir
	ModelMapper forRequest();  //request nesneleri için ModelMapper üretir
}
