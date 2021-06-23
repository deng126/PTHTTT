/*
#Khach hang
INSERT INTO `pthttt`.`khachhang` (`dia_chi`, `email`, `phone`, `ten`) VALUES ('a', 'a', 'a', 'a');
INSERT INTO `pthttt`.`khachhang` (`dia_chi`, `email`, `phone`, `ten`) VALUES ('b', 'b', 'b', 'b');
INSERT INTO `pthttt`.`khachhang` (`dia_chi`, `email`, `phone`, `ten`) VALUES ('c', 'c', 'c', 'c');

#Don hang
INSERT INTO `pthttt`.`donhang` (`mota`, `soluong`, `khachhangs_id`) VALUES ('a', '5', '1');
INSERT INTO `pthttt`.`donhang` (`mota`, `soluong`, `khachhangs_id`) VALUES ('b', '10', '2');
INSERT INTO `pthttt`.`donhang` (`mota`, `soluong`, `khachhangs_id`) VALUES ('c', '10', '3');

#NVL
INSERT INTO `pthttt`.`nvl` (`gia`, `ngay_nhap`, `so_luong`, `ten`) VALUES ('1000', '13-05-2021', '5', 'a'); 
INSERT INTO `pthttt`.`nvl` (`gia`, `ngay_nhap`, `so_luong`, `ten`) VALUES ('2000', '13-05-2021', '10', 'b');

#Nha cung cap
INSERT INTO `pthttt`.`nha_cung_cap` (`diachi`, `name`, `phone`) VALUES ('a', 'a', '123');
INSERT INTO `pthttt`.`nha_cung_cap` (`diachi`, `name`, `phone`) VALUES ('b', 'b', '456');

#Nhan vien
INSERT INTO `pthttt`.`nhanvien` (`dia_chi`, `email`, `luong`, `phone`, `ten`, `vaitro`) VALUES ('a', 'a', '10', '10', 'a', 'qlsx');
INSERT INTO `pthttt`.`nhanvien` (`dia_chi`, `email`, `luong`, `phone`, `ten`, `vaitro`) VALUES ('a', 'a', '10', '10', 'a', 'qlk');
INSERT INTO `pthttt`.`nhanvien` (`dia_chi`, `email`, `luong`, `phone`, `ten`, `vaitro`) VALUES ('a', 'a', '10', '10', 'a', 'qldh');
INSERT INTO `pthttt`.`nhanvien` (`dia_chi`, `email`, `luong`, `phone`, `ten`, `vaitro`) VALUES ('a', 'a', '10', '10', 'a', 'nvtk');
INSERT INTO `pthttt`.`nhanvien` (`dia_chi`, `email`, `luong`, `phone`, `ten`, `vaitro`) VALUES ('a', 'a', '10', '10', 'a', 'qtv');
INSERT INTO `pthttt`.`nhanvien` (`dia_chi`, `email`, `luong`, `phone`, `ten`, `vaitro`) VALUES ('b', 'b', '20', '20', 'b', 'may');

#Tai khoan
INSERT INTO `pthttt`.`tai_khoan` (`password`, `username`, `nhanviens_id`) VALUES ('1234', 'qlsx', '1');
INSERT INTO `pthttt`.`tai_khoan` (`password`, `username`, `nhanviens_id`) VALUES ('1234', 'qlk', '2');
INSERT INTO `pthttt`.`tai_khoan` (`password`, `username`, `nhanviens_id`) VALUES ('1234', 'qldh', '3');
INSERT INTO `pthttt`.`tai_khoan` (`password`, `username`, `nhanviens_id`) VALUES ('1234', 'nvtk', '4');
INSERT INTO `pthttt`.`tai_khoan` (`password`, `username`, `nhanviens_id`) VALUES ('1234', 'qtv', '5');

#Bo phan
INSERT INTO `pthttt`.`bo_phan` (`ten`, `nhanviens_id`) VALUES ('a', '1');
INSERT INTO `pthttt`.`bo_phan` (`ten`, `nhanviens_id`) VALUES ('b', '2');


#Quy trinh
INSERT INTO `pthttt`.`quy_trinh` (`maymoc`, `ten`, `bophans_id`) VALUES ('may khau', 'khau', '1');
INSERT INTO `pthttt`.`quy_trinh` (`maymoc`, `ten`, `bophans_id`) VALUES ('may may', 'may', '2');


#Ban thanh pham
INSERT INTO `pthttt`.`ban_thanh_pham` (`mota`, `ngaybd`, `soluong`, `quytrinhs_id`, `name`) VALUES ('a', '11-05-2021', '3', '1','ao the thao');
INSERT INTO `pthttt`.`ban_thanh_pham` (`mota`, `ngaybd`, `soluong`, `quytrinhs_id`, `name`) VALUES ('b', '11-05-2021', '5', '1','quan');
INSERT INTO `pthttt`.`ban_thanh_pham` (`mota`, `ngaybd`, `soluong`, `quytrinhs_id`, `name`) VALUES ('c', '12-05-2021', '4', '1', 'ao somi');


#Thanh pham
INSERT INTO `pthttt`.`thanh_pham` (`ngaykt`, `soluong`) VALUES ('12-05-2021', '4');
INSERT INTO `pthttt`.`thanh_pham` (`gia`, `ngaykt`, `soluong`, `ten`) VALUES ('1000', '12-05-2021', '3', 'quan');

/*
select *from pthttt.ban_thanh_pham b, pthttt.quy_trinh q
where b.quytrinhs_id = q.id
and q.ten like 'may'
*/


