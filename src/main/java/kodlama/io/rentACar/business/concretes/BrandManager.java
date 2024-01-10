package kodlama.io.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.IBrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.requests.UpdateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandResponse;
import kodlama.io.rentACar.business.rules.BrandBusinessRules;
import kodlama.io.rentACar.core.utilities.mappers.IModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.IBrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;

@Service //Bu sınıf bir Business nesnesidir (diyoruz Spring'e)
@AllArgsConstructor
public class BrandManager implements IBrandService{
	private IBrandRepository brandRepository;
	private IModelMapperService modelMapperService;
	private BrandBusinessRules brandBusinessRules;
	
	@Autowired //Autowired şunu yapar: 1) constructor'a parametrelerine bak der-> brandRepository 2) IBrandRepository'yi implemente
			   //edeni bul InMemoryBrandReposityory 3) onun (InMemoryBrandReposityory) new'lenmiş halini getir. 
	/*public BrandManager(IBrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}*/



	@Override
	public List<GetAllBrandsResponse> getAll() {
		
		List<Brand> brands = brandRepository.findAll();
	
		List<GetAllBrandsResponse> brandsResponse = brands.stream()
				.map(brand -> this.modelMapperService.forResponse().map(brand, GetAllBrandsResponse.class))
				.collect(Collectors.toList());
		
		return brandsResponse;	
	}



	@Override
	public void add(CreateBrandRequest createBrandRequest) {
		this.brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());
		
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		
		this.brandRepository.save(brand);
		
	}



	@Override
	public GetByIdBrandResponse getById(int id) {
		Brand brand = this.brandRepository.findById(id).orElseThrow();

		GetByIdBrandResponse response  = this.modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);
		
		return response;
	}



	@Override
	public void update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		
	}



	@Override
	public void delete(int id) {
		this.brandRepository.deleteById(id);
		
	}
	

}
