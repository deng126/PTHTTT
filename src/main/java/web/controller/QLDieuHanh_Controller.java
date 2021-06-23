package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import web.entity.*;
import web.repo.BaoCaoTonKhoRepository;
import web.repo.HoaDonNhapRepository;
import web.repo.HoaDonXuatRepository;
import web.repo.NVLRepository;


@Controller
@RequestMapping("/dieuhanh")
public class QLDieuHanh_Controller {
    private final HoaDonNhapRepository hdnrepo;
    private final HoaDonXuatRepository hdxrepo;
    private final NVLRepository nvlrepo;
    private final BaoCaoTonKhoRepository bctkrepo;

    @Autowired
    public QLDieuHanh_Controller(HoaDonNhapRepository hdnrepo, HoaDonXuatRepository hdxrepo,NVLRepository nvlrepo, BaoCaoTonKhoRepository bctkrepo){
        this.hdnrepo=hdnrepo;
        this.hdxrepo=hdxrepo;
        this.nvlrepo=nvlrepo;
        this.bctkrepo=bctkrepo;
    }
    
    @GetMapping("/nhapkho")
    public String getAll(Model model) {
        model.addAttribute("hdn", hdnrepo.findAllByTrangThaiContaining("request"));
        return "qldh/nhapkho";
    }
    
    @GetMapping("/nhapkho/{id}")
    public String nhapkho(@PathVariable Long id){
        HoaDonNhap hdn=hdnrepo.findById(id).get();
        NVL nvl=nvlrepo.findById(hdn.getNvls().getId()).get();
        
        nvl.setGia(hdn.getTongtien()+nvl.getGia());
        nvl.setNgayNhap(hdn.getNgayNhap());
        nvl.setSoLuong(hdn.getSoLuong()+ nvl.getSoLuong());
        
        hdnrepo.deleteById(id);
        nvlrepo.save(nvl);
        return "redirect:/bctk/getAll";
    }
    
    @GetMapping("/xuatkho")
    public String HDXuatKho(Model model) {
        model.addAttribute("xuatkho", hdxrepo.findAll());
        return "qldh/xuatkho";
    }
    
    @GetMapping("/xuatkho/{id}")
    public String xuatkho(@PathVariable Long id){
        HoaDonXuat hdx=hdxrepo.findById(id).get();
        NVL nvl=nvlrepo.findById(hdx.getNvls().getId()).get();
        BaoCaoTonKho bc=new BaoCaoTonKho();
        
        bc.setSoluongNhap(nvl.getSoLuong());
        bc.setSoluongXuat(hdx.getSoLuong());
        bc.setTongHangTon(nvl.getSoLuong()-hdx.getSoLuong());
        bc.setNhanviens((Nhanvien) hdx.getBophans().getNhanviens());
        bc.setNvls(hdx.getNvls());
        bctkrepo.save(bc);
        
        nvl.setGia(nvl.getGia()-hdx.getTongtien());
        nvl.setSoLuong(nvl.getSoLuong()-hdx.getSoLuong());
        
        hdnrepo.deleteById(id);
        nvlrepo.save(nvl);
        return "redirect:/bctk/getAll";
    }
}
