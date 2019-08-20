package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import dao.BeverageDAO;
import dao.OrderDAO;
import dao.UserDAO;
import model.BeverageVO;
import model.OrderVO;
import model.UserVO;
import service.PageService;
import service.UserService;

@Controller
@SessionAttributes({"userVO"})
@RequestMapping("/page")
public class PageController {
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	
	@Autowired
	UserService user;
	@Autowired
	UserDAO userDAO;
	@Autowired
	BeverageDAO beverageDAO;
	@Autowired
	OrderDAO orderDAO;
	@Autowired
	PageService page;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		return "page/index";
	}
	
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Model model, HttpServletRequest request,UserVO userVO,
			@RequestParam(value="code", defaultValue="") String code,
			@RequestParam(value="state", defaultValue="") String state) 
					throws UnsupportedEncodingException {
		String clientId = "6aRI5wmFjVe4vTZDOSBe";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "dyLBV6cJmx";// 애플리케이션 클라이언트 시크릿값";
		String redirectURI = URLEncoder.encode("http://localhost:8080/springPro/page/main", "UTF-8");
		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + clientId;
		apiURL += "&client_secret=" + clientSecret;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;
		System.out.println("apiURL=" + apiURL);
		HashMap<String, String> requestKey = getRequestKey(
				request, state, code, clientId, clientSecret);
		String access_token=requestKey.get("access_token");
		//String refresh_token=requestKey.get("refresh_token");
		String token_type = requestKey.get("token_type");
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		HashMap<String, Object> UserInfo = requestUserInfo(token_type, access_token);
		// email nickname profile_image age gender id birthday
		
		
		String naverEmail=(String) UserInfo.get("email");
		if(naverEmail==null) naverEmail="init";
		userVO = user.getUserInfo(naverEmail,true);
		if(userVO!=null) {
			model.addAttribute("userVO", userVO);
		}
		return "page/main";
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public String order(Model model) {
		return "page/order";
	}
	
	private HashMap<String, String> getRequestKey(HttpServletRequest request, 
			  String state, String code, String clientId, String naverClientSecret) {
	        String naverUrl = "https://nid.naver.com/oauth2.0/token?client_id=" + clientId + "&client_secret=" + naverClientSecret + "&grant_type=authorization_code" + "&state=" + state + "&code=" + code;
	        String naverJsonKey = "";
	        HashMap<String, String> result = new HashMap<String, String>();
	 
	        try {
	            HttpURLConnection conn = (HttpURLConnection) new URL(naverUrl).openConnection();
	            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	            String input;
	            while ((input = reader.readLine()) != null) {
	                naverJsonKey += input;
	            }
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	         
	        try {
	            JSONParser parser = new JSONParser();
	            JSONObject jsonObject = (JSONObject) parser.parse(naverJsonKey);
	             
	            result.put("access_token", (String) jsonObject.get("access_token"));
	            result.put("refresh_token", (String) jsonObject.get("refresh_token"));
	            result.put("token_type", (String) jsonObject.get("token_type"));
	             
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
	public HashMap<String, Object> requestUserInfo(String token_type, String access_token) {
       
	        String url = "https://apis.naver.com/nidlogin/nid/getUserProfile.xml";
	        String naverResult = "";
	        try {
	            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
	            conn.setRequestMethod("GET");
	            conn.setRequestProperty("Authorization", token_type + " " + access_token);
	            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	            String input;
	            while ((input = reader.readLine()) != null) {
	                naverResult += input;
	            }
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        HashMap<String, Object> result = new HashMap<String, Object>();
	 
	        try {
	            InputSource is = new InputSource(new StringReader(naverResult));
	            Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
	            XPath xpath = XPathFactory.newInstance().newXPath();
	            Node email = (Node) xpath.evaluate("//data/response/email", xml, XPathConstants.NODE);
	            Node nickname = (Node) xpath.evaluate("//data/response/nickname", xml, XPathConstants.NODE);
	            Node profile_image = (Node) xpath.evaluate("//data/response/profile_image", xml, XPathConstants.NODE);
	            Node age = (Node) xpath.evaluate("//data/response/age", xml, XPathConstants.NODE);
	            Node gender = (Node) xpath.evaluate("//data/response/gender", xml, XPathConstants.NODE);
	            Node id = (Node) xpath.evaluate("//data/response/id", xml, XPathConstants.NODE);
	            Node birthday = (Node) xpath.evaluate("//data/response/birthday", xml, XPathConstants.NODE);
	            if (email!=null) result.put("email", email.getTextContent());
	            if (nickname!=null) result.put("nickname", nickname.getTextContent());
	            if (profile_image!=null) result.put("profile_image", profile_image.getTextContent());
	            if (age!=null) result.put("age", age.getTextContent());
	            if (gender!=null) result.put("gender", gender.getTextContent());
	            if (id!=null) result.put("id", id.getTextContent());
	            if (birthday!=null) result.put("birthday", birthday.getTextContent());
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
	
	@RequestMapping(value = "/beverageList/{pageNum}", method = RequestMethod.GET)
	public String beverageList(Model model,
			@PathVariable("pageNum") String pageNum ) {
		page.getBeverageList(model, Integer.parseInt(pageNum));
		return "page/beverageList";
	}
	
	@RequestMapping(value = "/beverage_detail/{no}", method = RequestMethod.GET)
	public String beverage_detail(Model model,  @PathVariable("no") String no) {
		BeverageVO beverageVO=beverageDAO.selectBeverageByPK(Integer.parseInt(no));
		model.addAttribute("beverageVO",beverageVO);
		return "page/beverage_detail";
	}
	
	@RequestMapping(value = "/insertOrder", method = RequestMethod.POST)
	public String addOrder(Model model,OrderVO orderVO,UserVO userVO) {
		page.purchaseService(orderVO,userVO, model);
		
		logger.info("음료 No."+orderVO.getBeverageNo()+"주문 추가");
		return "page/main";
	}
	
	//재구매 하는 경우
	@RequestMapping(value = "/addReOrder/{no}/{buyCount}", method = RequestMethod.GET)
	public String addReOrder(@PathVariable("no") int no,
			@PathVariable("buyCount") int buyCount) {
		page.repurchaseService(no,buyCount);
		logger.info("재구매 완료");
		return "redirect:/page/main";
	}
	
	@RequestMapping(value = "/orderList/{pageNum}", method = RequestMethod.GET)
	public String orderList(Model model,
			@PathVariable("pageNum") String pageNum ) {
		page.getOrderList(model,Integer.parseInt(pageNum));
		return "page/orderList";
	}
	
}
