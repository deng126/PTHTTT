	package web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.entity.BanThanhPham;
import web.entity.HoaDonNhap;
import web.entity.NVL;
import web.entity.ThanhPham;
import web.repo.HoaDonNhapRepository;
import web.repo.NVLRepository;

@Controller
@RequestMapping("/ycn")
public class YeuCauNhap_Controller {
	private final HoaDonNhapRepository hdnRepo;
	private final NVLRepository nvlRepo;
	
	@Autowired
	private EntityManager entitymanager;

	@Autowired
	public YeuCauNhap_Controller(HoaDonNhapRepository hdnRepo, NVLRepository nvlRepo) {
		super();
		this.hdnRepo = hdnRepo;
		this.nvlRepo = nvlRepo;
	}
	
// qlk
	@GetMapping("/getAll")
	public String getAll(Model model) {
		model.addAttribute("hdn", hdnRepo.findAllByTrangThaiContaining("request"));
		return "qlk/ycn";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteYCN(Model model, @PathVariable Long id) {
		hdnRepo.deleteById(id);;
		return "redirect:/ycn/getAll";
	}
	
	@GetMapping("/search")
	public String searchYCN(@Param("keyword") String keyword, Model model) throws ParseException {
		if(keyword=="") {
			return "redirect:/ycn/getAll";
		} else {
			// hql with relationship
			Query q = entitymanager.createQuery("SELECT hdn FROM HoaDonNhap AS hdn JOIN hdn.nvls AS hn WHERE hn.ten = :x AND hdn.trangThai = 'request' ");
			q.setParameter("x", keyword);
			
			List<HoaDonNhap> list = (List<HoaDonNhap>) q.getResultList();
			model.addAttribute("hdn", list);		
		}
		
		return "qlk/ycn";
	}
	
	@GetMapping("/confirm/{id}")
	public String confirm(@PathVariable Long id) {
		HoaDonNhap hdn = (HoaDonNhap) hdnRepo.findById(id).get();
		
		NVL nvl = new NVL();
		nvl = nvlRepo.findById(hdn.getNvls().getId()).get();
		nvl.setSoLuong(nvl.getSoLuong() + hdn.getSoLuong());
		nvlRepo.save(nvl);
		
		Date date = new Date();
		String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
		hdn.setNgayNhap(currentDate);
		
		hdn.setTrangThai("confirm");
		hdn.setTongtien(hdn.getSoLuong()*nvl.getGia());
		hdnRepo.save(hdn);
		return "redirect:/ycn/getAll";
	}
	
}
