
#### Dưới đây là bản Markdown mô tả dự án của bạn, được viết một cách chuyên nghiệp và chi tiết:

# Comic Book Store Management System
## Mô tả dự án
#### Dự án này là một hệ thống quản lý cửa hàng truyện tranh được xây dựng bằng Java. Hệ thống bao gồm hai vai trò chính: Admin và Customer. Các chức năng của hệ thống bao gồm quản lý sách, mua bán và thuê truyện, cùng với việc quản lý các yêu cầu của khách hàng.

## Các tính năng chính
### 1. Quản lý sách (Admin)
#### Thêm sách: Admin có thể thêm sách mới vào hệ thống.
####  Sửa sách: Admin có thể chỉnh sửa thông tin của sách (như giá, thể loại, số lượng, v.v...).
####   Xóa sách: Admin có thể xóa sách khỏi hệ thống.
####   Xác nhận yêu cầu mua truyện: Admin duyệt và xác nhận các yêu cầu mua truyện từ khách hàng.
####   Xác nhận yêu cầu thuê truyện: Admin duyệt và xác nhận các yêu cầu thuê truyện.
####  Xác nhận yêu cầu trả truyện: Admin duyệt và xác nhận các yêu cầu trả truyện từ khách hàng.
   ### 2. Các hoạt động của khách hàng
   #### Xem sách: Khách hàng có thể xem danh sách các sách có sẵn trong kho, bao gồm các thông tin như tên, thể loại, giá, đánh giá và tình trạng sách.
   #### Đánh giá sách: Khách hàng có thể đánh giá sách với điểm số từ 1 đến 5.
   #### Mua sách: Khách hàng có thể mua sách, yêu cầu sẽ được gửi đến Admin để duyệt. Số lượng mua không được vượt quá số lượng sách còn trong kho.
   #### Thuê sách: Khách hàng có thể thuê sách, yêu cầu cũng sẽ được Admin duyệt. Khách hàng cần phải thanh toán tiền cọc và phí thuê trước khi nhận sách.
   #### Trả sách: Khách hàng có thể trả sách đã thuê. Tình trạng sách sẽ được kiểm tra và Admin sẽ xử lý yêu cầu trả sách.
#### Xem lịch sử: Mua, thuê và trả hàng
#### Xem trạng thái đơn hàng
   # Cấu trúc dự án
   ### 1. Các lớp và giao diện chính
  #### Book: Đại diện cho thông tin sách, bao gồm các thuộc tính như ID, tên, thể loại, giá, số lượng trong kho, và tình trạng sách (mới hoặc rách).
  #### Ticket: Đại diện cho phiếu mua sách của khách hàng, chứa thông tin về tên khách hàng, số điện thoại, tên sách và số lượng mua.
  #### TicketRentBook: Đại diện cho phiếu thuê sách của khách hàng, bao gồm thông tin về tên khách hàng, số điện thoại, tên sách, số lượng thuê và ngày trả sách.
#### TicketReturnBook: Đại diện cho phiếu trả sách của khách hàng, chứa thông tin về tên khách hàng, số điện thoại, tên sách, số lượng trả và tình trạng sách.
#### AdminService: Chứa các phương thức xử lý hành động của admin như thêm, sửa, xóa sách, xác nhận yêu cầu mua/thuê/trả sách.
#### CustomerService: Chứa các phương thức xử lý hành động của khách hàng như xem sách, đánh giá sách, mua/thuê/trả sách.
#### IActionService: Giao diện chung cho các dịch vụ quản lý hành động của đối tượng như thêm, sửa, xóa, cập nhật thông tin.
####  InvalidPhoneNumberException: Lớp ngoại lệ dùng để xử lý các trường hợp số điện thoại không hợp lệ.
   ### 2. Các lớp chính trong hệ thống
  #### AdminMenu: Giao diện menu dành cho Admin để lựa chọn các chức năng quản lý sách và duyệt các yêu cầu của khách hàng.
  #### CustomerMenu: Giao diện menu dành cho khách hàng để lựa chọn các chức năng như xem sách, đánh giá, mua/thuê/trả sách.
   # Mô hình hoạt động
  ## Admin có thể:

### + Thêm, sửa, xóa thông tin sách trong hệ thống.
### + Duyệt các yêu cầu mua sách và thuê sách từ khách hàng.
### + Duyệt yêu cầu trả sách từ khách hàng.
 ## Customer có thể:

### + Xem danh sách sách có sẵn trong kho.
### + Đánh giá sách đã đọc.
### + Mua sách nếu có đủ số lượng trong kho.
### + Thuê sách và thanh toán tiền cọc cùng phí thuê sách.
### + Trả sách đã thuê và xác nhận tình trạng sách.
### + Xem lịch sử mua, thuê và trả hàng
### + Xem trạng thái đơn hàng
###  Các lớp và phương thức chính
#### - Lớp Book
#### - Lớp Ticket
#### - Lớp TicketRentBook
#### - Lớp AdminService
#### - Lớp CustomerService
# Cách chạy dự án
### - Khởi động dự án: Mở và chạy lớp chính, từ đó sẽ hiển thị menu cho Admin hoặc Customer để thực hiện các chức năng.
### - Thêm sách: Admin có thể thêm sách vào hệ thống thông qua menu quản lý.
### - Xem sách và mua/thuê: Customer có thể xem các sách trong kho và thực hiện các hành động mua hoặc thuê sách.
##  Yêu cầu hệ thống
### - Java 8 trở lên.
### - Thư viện java.time được sử dụng để xử lý ngày tháng.
### - Hệ thống có thể mở rộng để tích hợp với cơ sở dữ liệu nếu cần.
# Kết luận
### Dự án quản lý cửa hàng truyện tranh này cung cấp một hệ thống quản lý đơn giản nhưng đầy đủ chức năng cho cả admin và khách hàng. Với khả năng quản lý sách, xử lý yêu cầu mua/thuê/trả sách, và đánh giá sách, hệ thống này có thể mở rộng để đáp ứng nhu cầu ngày càng cao của người dùng trong tương lai.