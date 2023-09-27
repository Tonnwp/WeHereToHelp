# CS211 - Project<br>
## วิธีทดสอบการ RUN<br> ##
1. Main <br>
   run Main Class
2. javafx plugin<br>
   MVN Clean<br>
   javafx -> javafx:run<br><br>

วิธีสร้าง Jar<br>
MVN Clean<br>
MVN install<br><br>
file จะอยู่ใน target เป็น shade.jar

## การติดตั้งและรันโปรแกรม ##

1.เปิด Terminal

1.โคลน Repository
 ```
git clone https://github.com/CS211-651/project211-onenightmiracle.git 
```
2.พิมพ์
```
cd/YourDirectorySaved/project211-onenightmiracle/run
```
3.รันโปรแกรม
```
java -jar <jar-file-name>.jar
```

## การวางโครงสร้างFile ##

**1.data<br>**
เก็บข้อมูลของผู้สมัครในไฟล์ Users.csv<br>
เก็บข้อมูลเนื้อหาร้องเรียนในไฟล์ Complaints.csv

**2.images<br>**
เก็บไฟล์รูปภาพทั้งหมดเมื่อมีการเปลี่ยนรูปภาพ

**3.controllers<br>**
เก็บไฟล์ controllers ทั้งหมด<br>
- AdminProfileListController โค้ดส่วนของหน้า Admin Team ในโปรแกรม โดยแสดง ชื่อ รหัสนิสิต และชื่อเล่น ของผู้เขียนโปรแกรม
- ChangePasswordController โค้ดส่วนของหน้า Change password ที่อยู่ในหน้า Profile ของโปรแกรม โดยต้องระบุ Username Password ก่อนหน้า และ Password ใหม่
- ComplaintCategoryController โค้ดส่วนของหน้า Compliant Category ที่สามารถโหวตคะแนนให้กับหัวข้อร้องเรียนที่สนใจได้
- ComplaintController โค้ดส่วนของหน้า Complaint ที่ให้ผู้ใช้งานสามารถเขียนร้องเรียน และให้เลือกหมวดหมู่ที่ผู้เขียนต้องการร้องเรียนโดยตรง
- ComplaintStatusController โค้ดส่วนของหน้า ComplaintStatus ที่สามารถให้ผู้ใช้งานเช็คว่าหัวข้อร้องเรียนอยู่ในช่วง Not Complete,Progressing,Completed และผู้ใช้งานสามารถกดปุ่มเพื่อ เรียงตามเวลาที่เขียนหรือเรียงตามคะแนนโหวต
- GuideForUserController โค้ดส่วนของหน้า Guide มีเพียงใส่ปุ่ม Back
- HomeController โค้ดส่วนของหน้าหลักหลังจาก Login เข้ามาแล้วโดยเป็นหน้าสำหรับเปลี่ยนไปหน้าต่างๆ
- LoginController โค้ดส่วนของหน้า Login สำหรับกรอกรหัสเข้าใช้ระบบ และ ระบบ SignUp สำหรับ User และ เจ้าหน้าที่(Officer)
- MyComplaintController โค้ดส่วนของหน้า MyComplaint ที่จะแสดงเฉพาะข้อมูลที่ผู้ใช้เป็นคนเขียนร้องเรียนพร้อมแสดงสถานะและรายละเอียด
- OfficerOnlyComplaintController โค้ดส่วนของหน้า StaffOnly ที่เข้าได้เฉพาะเจ้าหน้าที่เท่านั้นโดยเจ้าหน้าที่สามารถเปลี่ยนสถานะคำร้องเรียน และเจ้าหน้าที่สามารถกรอกข้อมูลตอบกลับได้
- OfficerRegisterController โค้ดส่วนของหน้า OfficerRegister สำหรับผู้ใช้งานที่สนใจจะสมัครเป็นหน่วยงานเจ้าหน้าที่ โดยจะต้องกรอกข้อมูลต่างๆและประเภทหน่วยงานที่ต้องการสมัคร
- ProfileController โค้ดส่วนของหน้า Profile จะแสดง Username DisplayName และ หน่วยงาน(เมื่อ Login ด้วยรหัสเจ้าหน้าที่)เป็นหน้าที่ผู้ใช้งานสามารถเปลี่ยนรูปได้โดยกดที่ปุ่ม Upload Image และ สามารถเปลี่ยนรหัสผ่านได้โดยกดปุ่ม Change Password
- UserRegisterController โค้ดส่วนของหน้า SignUp เพื่อให้ผู้ใช้งานสร้างรหัสของตัวเองเพื่อนำไปใช้ในขั้นตอน Login และขั้นตอนอื่นๆ

**4.models<br>**
เก็บไฟล์ Model และ ModelList
- AdminModel โค้ดส่วนนี้รับ extends จาก UserModel
- AdminProfile โค้ดส่วนนี้จะมี name id nickName สำหรับหน้าเก็บข้อมูล admin หน้า AdminTeam
- AdminProfileList โค้ดส่วนนี้มี method เก็บและโชว์ Profile ของแอดมิน
- ComplaintModel โค้ดส่วนนี้จะมี headline detail category date score status writeBy(ชื่อผู้ใช้ที่เขียนร้องเรียน) reply(ข้อความตอบกลับ) replyName(ชื่อเจ้าหน้าที่ตอบกลับ)
- ComplaintModelList โค้ดส่วนนี้จะมี method เก็บและโชว์ข้อมูลร้องเรียน
- OfficerModel โค้ดส่วนนี้รับ extends จาก UserModel แต่มี agency(หน้าที่,หน่วยงาน)เพิ่มขึ้นมา
- UserModel เป็น Model Super-class ของ AdminModel และ OfficerModel โค้ดส่วนนี้จะมี username password displayName imagePath(path ของรูปภาพ) date(วันที่และเวลาที่เข้าใช้งาน)
- UserModelList โค้ดส่วนนี้จะมี method เก็บและโชว์ข้อมูลของ user officer และ admin

**5.services**
เก็บ Interface ของ DataSource Filterer และ ListDataSource สำหรับอ่านและเขียนไฟล์ใน csv

## สิ่งที่พัฒนาแต่ละความก้าวหน้าของระบบ ##
**ชื่อ Controller Model และ Fxml อ้างอิงจารชื่อปัจจุบัน**

### ความก้าวหน้าของระบบครั้งที่ 1  ###
มีการสร้างหน้าและเชื่อมหน้าโดยใช้ FXRouter

**สร้างหน้า Fxml**
- login.fxml
- home.fxml
- user_register.fxml
- change_password.fxml
- complaint.fxml
- admin_profile.fxml

**สร้างหน้า Controller**
- LoginController
- HomeController
- UserRegisterController
- ChangePassWordController
- ComplaintController
- AdminProfileController

### ความก้าวหน้าของระบบครั้งที่ 2  ###
- ปรับปรุงหน้า UI ใหม่ทั้งหมดให้ดูสวยงามและเข้าใจง่ายต่อการใช้มากยิ่งขึ้น
- หน้า user_register สามารถกรอกข้อมูลและบันทึกข้อมูลลงใน CSV ผ่าน UserRegisterController
- หน้า admin_profile ได้เพิ่ม ListView และสามารถกดเลือกชื่อแอดมินในตารางและแสดงข้อมูลต่างๆของแอดมิน พร้อมกับรูปภาพของแอดมิน

**สิ่งที่สร้างเพิ่มเข้ามา**

- UserModel
- UserModelList
- officer_register.fxml
- AdminProfileHardCodeDataSource
- Directory icon,images และ color ใช้เก็บรูปภาพในการทำแอพพลิเคชั่นกับรหัสสีที่ใช้

### ความก้าวหน้าของระบบครั้งที่ 3  ###
- ระบบการสร้างบัญชีของผู้ใช้งานทั่วไปโดยมีการเช็ค Username ว่าซ้ำหรือไม่และเมื่อสร้างบัญชีแล้วสามารถนำไปใช้ Login ได้
- ระบบการ Login เมื่อใส่ Username หรือรหัสผิดจะไม่สามารถเข้าใช้ได้ และระบบเปลี่ยนรหัสผ่านใช้งานได้
- หน้าโปรไฟล์สามารถแสดงชื่อของผู้ล็อคอินได้และเปลี่ยนรูปภาพได้

**สิ่งที่สร้างเพิ่มเข้ามา**
- OfficerRegisterController
- OfficerModel
- OfficerModelList

### ความก้าวหน้าของระบบครั้งที่ 4  ###
- หน้า ComplaintCategory สามารถเลือกเฉพาะหมวดหมู่ที่สนใจได้ผ่าน choiceBox และสามารถ vote ให้คะแนนเรื่องร้องเรียนได้
- หน้า ComplaintStatus สามารถเลือกดูเฉพาะสถานะเรื่องร้องเรียนที่ต้องการได้ และสามารถกดปุ่มเพื่อเรียงวันที่และเวลาจากวันที่เขียนร้องเรียนจากเก่าไปใหม่ หรือ สามารถกดปุ่มเพื่อเรียงตามคะแนนโหวตจากมากไปน้อยได้ เมื่อ Login ด้วยรหัสเจ้าหน้าที่จะสามารถเห็นชื่อเจ้าหน้าที่ที่ข้อความตอบกลับแต่จะเห็นได้เฉพาะเจ้าหน้าที่หน่วยงานเดียวกัน
- สร้างหน้า GuideForUser เป็นหน้าคำแนะนำวิธีใช้แอพพลิเคชั่นเบื้องต้น
- หน้า OfficerOnlyComplaint หน้าสำหรับเจ้าหน้าที่เท่านั้นจะแสดงข้อมูลตามหน่วยงานที่สมัครไว้ กดปรับเปลี่ยนสถานะคำร้องเรียน สามารถพิมข้อความตอบกลับ และสามารถเลือกดูตามสถานะของเรื่องร้องเรียนได้
- หน้า Profile เมื่อ Login ด้วยเจ้าหน้าที่หรือแอดมินจะมีบรรทัดแสดงหน่วยงานขึ้นมา
- หน้า MyComplaint โชว์เฉพาะเรื่องร้องเรียนที่ผู้ใช้บัญชีเป็นคนเขียนร้องเรียง
- เมื่อ Login จะมีตัวสแตมป์เวลาตอนที่ผู้ใช้ Login และนำไปแสดงหน้า Profile

**สิ่งที่สร้างเพิ่มเข้ามา**
- complaint_category.fxml
- complaint_status.fxml
- guide_for_user.fxml
- officer_only_complaint.fxml
- my_complaint.fxml
- ComplaintCategoryController
- ComplaintStatusController
- GuideForUserController
- MyComplaintController
- OfficerOnlyComplaintController
- AdminModel
- ComplaintModel
- ComplaintModelList
- Filterer












