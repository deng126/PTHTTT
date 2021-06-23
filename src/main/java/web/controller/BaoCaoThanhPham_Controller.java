package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.entity.BaoCaoThanhPham;
import web.repo.BaoCaoThanhPhamRepository;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("bctp")
public class BaoCaoThanhPham_Controller {
    private final BaoCaoThanhPhamRepository bctp_repo;
    @Autowired
    private EntityManager entitymanager;
    @Autowired
    public BaoCaoThanhPham_Controller(BaoCaoThanhPhamRepository bctp_repo){
        this.bctp_repo=bctp_repo;
    }
    @GetMapping("/getAll")
    public String getAll(Model model){
        model.addAttribute("bctp", bctp_repo.findAll());
        return "qldh/bctp";
    }
    @GetMapping("/add")
    public String addBctp(Model model){
        model.addAttribute("bctp",new BaoCaoThanhPham());
        return "qldh/formbctp";
    }
    @GetMapping("/edit/{id}")
    public String editBctp(Model model, @PathVariable Long id){
        model.addAttribute("bctp",bctp_repo.findById(id).get());
        return "qldh/formbctp";
    }
    @GetMapping("/delete/{id}")
    public String deleteBctp(@PathVariable Long id){
        bctp_repo.deleteById(id);
        return "redirect:/bctp/getAll";
    }
    @GetMapping("/search")
    public String searchDonhang(@Param("keyword") String ngaybd, String ngaykt, Model model) throws ParseException {
        Date bd=new SimpleDateFormat("yyyy-MM-dd").parse(ngaybd);
        Date kt=new SimpleDateFormat("yyyy-MM-dd").parse(ngaykt);
        Query q= entitymanager.createQuery("select bc from BaoCaoThanhPham as bc join bc.thanhphams as tp where tp.ngayKT between :x and :y");
        q.setParameter("x", bd);
        q.setParameter("y", kt);
        List<BaoCaoThanhPham> list= (List<BaoCaoThanhPham>) q.getResultList();
        model.addAttribute("bctp", list);
        return "qldh/bctp";
    }
    @PostMapping("/save")
    public String addBctp(BaoCaoThanhPham bc){
        bctp_repo.save(bc);
        return "redirect:/bctp/getAll";
    }
}
