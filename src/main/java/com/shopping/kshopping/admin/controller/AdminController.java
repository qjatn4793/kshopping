package com.shopping.kshopping.admin.controller;

import com.shopping.kshopping.admin.service.AdminService;
import com.shopping.kshopping.admin.vo.AdminVo;
import com.shopping.kshopping.configuration.SHA256;
import com.shopping.kshopping.login.vo.LoginVo;
import com.shopping.kshopping.product.service.ProductService;
import com.shopping.kshopping.product.vo.ProductVo;
import com.shopping.kshopping.util.UploadFileUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import static java.lang.String.valueOf;


@AllArgsConstructor
@ResponseBody
@RestController
public class AdminController {

    /*@GetMapping("/admin/viewProduct")
    public String viewProduct(ProductVo productVo){

        return null;
    }*/

    AdminService adminService;
    ProductService productService;

    @PostMapping("/admin")
    public int adminLoginCheck(@RequestBody AdminVo adminVo, HttpServletRequest request)throws NoSuchAlgorithmException {
        SHA256 sha256 = new SHA256();

        int adminLoginCheck = adminService.adminLoginCheck(adminVo);
        String adminPw = adminService.adminSelectOne(adminVo.getAdminId());
        String encryptAdminPw = "";

        // nullpointexception 처리
        try{
            encryptAdminPw = sha256.encrypt(adminPw);
        }catch (NullPointerException e) {
            adminLoginCheck = 0;
            return adminLoginCheck;
        }

        HttpSession session = request.getSession();

        if(encryptAdminPw.equals(sha256.encrypt(adminVo.getAdminPw()))) {
            if (adminLoginCheck == 1) {
                session.setAttribute("adminLoginCheck", "success");
                session.setAttribute("adminId", adminVo.getAdminId());
                session.setAttribute("adminPw", sha256.encrypt(adminVo.getAdminPw()));

                return adminLoginCheck;
            } else {
                adminLoginCheck = 0;
                return adminLoginCheck;
            }
        }else {
            adminLoginCheck = 0;
            return adminLoginCheck;
        }
    }


    @DeleteMapping("/admin")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("adminLoginCheck");
        session.removeAttribute("adminId");
        session.removeAttribute("adminPw");
        session.invalidate();
        return "adminLogout";
    }

    @GetMapping("/admin/adminGetUser") // 관리자 - 사용자 관리
    public HashMap<String, LoginVo> adminGetUser(HttpServletRequest request) throws Exception{
        HashMap<String, LoginVo> adminGetUser = new HashMap<>();

        int boardCount = adminService.adminGetUserCount();

        for (int i=1; boardCount >= i; i++) {
            if(adminService.adminGetUser(i) != null){
                adminGetUser.put(valueOf(i), adminService.adminGetUser(i));
            }
        }
        return adminGetUser;
    }

    @DeleteMapping("/admin/adminUserDelete") // 관리자 - 사용자 삭제
    public int adminUserDelete(@RequestBody LoginVo loginVo) throws Exception{
        if (loginVo == null){
            return 0;
        }else {
            return adminService.adminUserDelete(loginVo.getUserSeq());
        }
    }

    @PutMapping("/admin/adminUserUpdate") // 관리자 - 사용자 수정
    public int adminUserUpdate(@RequestBody LoginVo loginVo) throws Exception{
        if (loginVo == null){
            return 0;
        }else {
            return adminService.adminUserUpdate(loginVo);
        }
    }

    @PutMapping("/admin/passwordReset") // 관리자 - 패스워드 초기화
    public int passwordReset(@RequestBody LoginVo loginVo) throws Exception{

        if (loginVo == null){
            return 0;
        }else {
            return adminService.passwordReset(loginVo);
        }
    }

    @PostMapping("/admin/adminDesign/{fileName}")
    public int adminDesign(@PathVariable("fileName") String fileName, @RequestBody HashMap<String, String> uploadPath) throws Exception{

        String uploadRealPath = uploadPath.get("uploadPath");
        String realFileName = fileName + ".jpg";

        // TODO : 프로젝트 경로가 바뀌면 상기 경로 확인 후 변경해줘야함
        String path = System.getProperty("user.dir"); // 현재 경로 > C:\Users\PHS-SECURUS\Desktop\kshopping // C:\Users\kimbeomsoo\Desktop\apache-tomcat-9.0.60\bin
        /*개발시*/
        //String divPath = "\\src\\main\\resources\\static\\common\\assets\\img\\";
        /*끝*/
        /*배포시 windows*/
        //path = path.replace("bin", "webapps");
        //String divPath = "\\ROOT\\WEB-INF\\classes\\static\\common\\assets\\img\\";
        /*끝*/
        /*배포시 linux*/
        path = path.replace("bin", "webapps");
        String divPath = "/ROOT/WEB-INF/classes/static/common/assets/img/";
        /*끝*/
        String realPath = path + divPath;

        //File to Multipartfile
        File file = new File(uploadRealPath); // String to File
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", fileName, "text/plain", IOUtils.toByteArray(input));
        //여기까지

        if(multipartFile != null){
            File target = new File(realPath, realFileName);
            FileCopyUtils.copy(multipartFile.getBytes(), target);

            return 1;
        }else {
            return 0;
        }

    }

    @GetMapping("/admin/product")
    public HashMap<String, ProductVo> productViewAdmin() throws Exception{

        HashMap<String, ProductVo> productView = new HashMap<>();

        int productMaxCount = productService.productCount();
        int productMinCount = productService.productMinCount();

        for (int i=productMinCount; productMaxCount >= i; i++) {
            if(productService.productView(i) != null){
                productView.put(valueOf(i), productService.productView(i));
            }
        }

        return productView;
    }

    @PostMapping("/admin/product")
    public int productCreate(@RequestBody ProductVo productVo) throws Exception{
        String path = System.getProperty("user.dir"); // 현재 경로 > C:\Users\PHS-SECURUS\Desktop\kshopping
        // TODO : 프로젝트 경로가 바뀌면 상기 경로 확인 후 변경해줘야함

        //배포시 경로
        if(path != "" || path != null){
            path = path.replace("bin","webapps");
        }

        // windows
        //String divPath = "\\ROOT\\WEB-INF\\classes\\static\\common\\img\\";
        // linux
        String divPath = "/ROOT/WEB-INF/classes/static/common/img/";

        try {
            //File to Multipartfile
            System.out.println("1");
            String fileS = productVo.getProductImg();
            fileS = fileS.replace("C:\\uploadPath\\", "/temp/uploadPath/");

            File file = new File(fileS); // String to File
            System.out.println("2");

            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
            //여기까지
            String imgUploadPath = path + divPath + File.separator + "imgUpload";
            String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
            String fileName = null;

            if (multipartFile != null) {
                fileName = UploadFileUtils.fileUpload(imgUploadPath, multipartFile.getOriginalFilename(), multipartFile.getBytes(), ymdPath);
            } else {
                fileName = path + divPath + File.separator + "images" + File.separator + "none.png";
            }

            productVo.setProductImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
            productVo.setProductThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
        }catch (Exception e){
            System.out.println("================= 파일 업로드 에러 발생 ===============");
            System.out.println(e);
            System.out.println("================= 파일 업로드 에러 발생 ===============");
        }
        /*여기까지*/

        //개발시 경로
        /*String divPath = "\\src\\main\\resources\\static\\common\\img";

        //File to Multipartfile
        File file = new File(productVo.getProductImg()); // String to File
        try {
            FileInputStream input = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
            //여기까지
            String imgUploadPath = path + divPath + File.separator + "imgUpload";
            String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
            String fileName = null;

            if (multipartFile != null) {
                fileName = UploadFileUtils.fileUpload(imgUploadPath, multipartFile.getOriginalFilename(), multipartFile.getBytes(), ymdPath);
            } else {
                fileName = path + divPath + File.separator + "images" + File.separator + "none.png";
            }

            productVo.setProductImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
            productVo.setProductThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
        }catch (Exception e){
            System.out.println(e);
        }*/
        /*여기까지*/

        if (productVo == null){
            return 0;
        }else {

            HashMap<String, String> product = new HashMap<>();

            if (productVo.getProductContents1().length() > 199 && productVo.getProductContents2().length() > 199 && productVo.getProductContents3().length() > 199) {
                return 0;
            }else {
                product.put("productName", productVo.getProductName());
                product.put("productContents1", productVo.getProductContents1());
                product.put("productContents2", productVo.getProductContents2());
                product.put("productContents3", productVo.getProductContents3());
                product.put("productImg", productVo.getProductImg());
                product.put("productThumbImg", productVo.getProductThumbImg());
                product.put("productCategory", productVo.getProductCategory());
            }

            return productService.productCreate(product);
        }
    }

    @PutMapping("/admin/product")
    public int productUpdate(@RequestBody ProductVo productVo) throws Exception{

        if (productVo == null){
            return 0;
        }else {
            return productService.productUpdate(productVo);
        }
    }

    @DeleteMapping("/admin/product")
    public int productDelete(@RequestBody ProductVo productVo) throws Exception{

        if (productVo == null){
            return 0;
        }else {
            return productService.productDelete(productVo.getProductSeq());
        }
    }
}
