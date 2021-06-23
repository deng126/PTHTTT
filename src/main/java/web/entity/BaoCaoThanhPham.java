package web.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class BaoCaoThanhPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String danhgia;
	
	@ManyToOne(targetEntity = Nhanvien.class)
	private List<Nhanvien> nhanviens;
	
	@ManyToOne(targetEntity = ThanhPham.class)
	private List<ThanhPham> thanhphams;
}
