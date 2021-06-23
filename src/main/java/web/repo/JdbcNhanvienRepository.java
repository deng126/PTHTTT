package web.repo;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import web.entity.Nhanvien;
import web.entity.TaiKhoan;

@Repository
public class JdbcNhanvienRepository implements _NhanvienRepository{
	private JdbcTemplate jdbc;
	
	public JdbcNhanvienRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Long saveAndReturnId(Nhanvien nhanvien) {
		jdbc.update("INSERT INTO nhanvien(ten, dia_chi, email, phone, vaitro, luong) VALUES (?, ?, ?, ?, ?, ?)",
				nhanvien.getTen(), nhanvien.getDiaChi(), nhanvien.getEmail(), 
				nhanvien.getPhone(), nhanvien.getVaitro(), nhanvien.getLuong());
		return jdbc.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
	}

	@Override
	public TaiKhoan save(TaiKhoan taikhoan, Long nhanviens_id) {
		jdbc.update("INSERT INTO tai_khoan(username, password, nhanviens_id) VALUES(?, ?, ?)", 
				taikhoan.getUsername(), taikhoan.getPassword(), nhanviens_id);
		return taikhoan;
	}
}