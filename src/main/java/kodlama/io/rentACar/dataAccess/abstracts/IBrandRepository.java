package kodlama.io.rentACar.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.rentACar.entities.concretes.Brand;

//Repository ismi genellikle veritabanı işleri yapacak sınıflara verilir. Dao olarak ta adlandırılırlar

public interface IBrandRepository extends JpaRepository<Brand, Integer>{ 
	boolean existsByName(String name); //existsByName built-in bir metot değil ancak JpaRepository exist kelimesini tanıyıp bir true
									  // false döneceğini ve ByName kelimesini de tanıyıp name'e göre arama yapacağı anlar.
									  // spring jpa keywords ile daha fazlasını araştır
	
}
