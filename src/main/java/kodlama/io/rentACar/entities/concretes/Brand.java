package kodlama.io.rentACar.entities.concretes;

import java.util.List;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name="brands")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Brand {
	
	@Id                //veritabanında id kısmı primary key olduğunu söyledik
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Id' yi otomatik olarak (genillikle 1'er 1'er) artırır
	@Column(name="id") // veritabanındaki id sütunu ile eşleştirdik
	private int id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy = "brand")
	private List<Model> models;
	
}
	