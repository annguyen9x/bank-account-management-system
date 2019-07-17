# bank-account-management-system

Java Basic Application

(1) Ứng dụng mô tả hệ thống ngân hàng, chưa kết nối Cơ sở dữ liệu (dữ liệu lưu tại ArrayList và File), với các chức năng cơ bản:

- Các chức năng tại quầy giao dịch của ngân hàng
-	Chức năng trên Internet Banking
-	Các chức năng tại cây ATM

(2) Cụ thể, có 3 menu chức năng tương ứng:

*Lưu ý: Ở đây cần phân biệt 2 loại tài khoản khác nhau là: Tài khoản thành viên, số tài khoản ngân hàng (tài khoản ngân hàng) !
-	Menu 1: Đăng ký thành viên; Đăng nhập tài khoản thành viên; Nộp tiền vào tài khoản ngân hàng; Xem danh sách thành viên. => chức năng tại quầy giao dịch của ngân hàng.
-	Menu 2: Đăng ký thêm tài khoản ngân hàng; Xem danh sách tài khoản ngân hàng; Đổi mật khẩu đăng nhập tài khoản thành viên; Đăng nhập tài khoản ngân hàng. (Menu 2 chỉ xuất hiện sau khi "Đăng nhập tài khoản thành viên" ở Menu 1) => chức năng trên Internet Banking.
-	Menu 3: Rút tiền; Chuyển tiền; Thay đổi số Pin; Xem số dư; Xem nhật ký giao dịch. (Menu 3 chỉ xuất hiện sau khi "Đăng nhập tài khoản ngân hàng" ở Menu 2) => chức năng tại cây ATM.

(3) Phát biểu bài toán

-	[1] Hệ thống cho phép khách hàng thực hiện các chức năng: Đăng ký thành viên, đăng nhập tài khoản thành viên, nộp tiền vào tài khoản, xem danh sách khách hàng thành viên.
-	[2] Khách hàng đăng ký thành viên trên hệ thống gồm các thông tin: Họ tên, cmnd, ngày sinh. Hệ thống tự động cấp mã khách hàng, cấp mật khẩu mặc định là cmnd. Sau khi đăng ký thành viên, khách hàng được hệ thống tự động cấp một số tài khoản ngân hàng (tài khoản ngân hàng) với số dư mặc định là 100k, và cấp số Pin mặc định là số nguyên có 6 chữ số được Random ngẫu nhiêu.
-	[3] Khách hàng thành viên sau khi đăng nhập được mở nhiều tài khoản ngân hàng trên hệ thống, được xem danh sách tài khoản ngân hàng đang sở hữu, được đổi mật khẩu đăng nhập tài khoản thành viên, và được đăng nhập vào tài khoản ngân hàng để thực hiện các giao dịch trên máy ATM.
-	[4] Sau khi đăng nhập tài khoản ngân hàng được thực hiện các giao dịch trên máy ATM: Rút tiền, chuyển tiền, xem số dư, thay đổi pin, xem nhật ký giao dịch.

