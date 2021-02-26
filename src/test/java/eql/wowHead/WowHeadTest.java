package eql.wowHead;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class WowHeadTest {
	
	WebDriver driver;
	
	private String BROWSER = System.getProperty("browser");

	
	@Before
	
	public void setup() {
		

	//Instancier les navigateurs pour pouvoir les choisir en tant que propriétés dans Jenkins
		if (BROWSER.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver2.exe");
			driver = new ChromeDriver();			
					}
		else if (BROWSER.equalsIgnoreCase("explorer")) {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/geckodriver.exe");
			driver = new FirefoxDriver();
	
	}}

	@After
	
	public void teardown() {
	driver.quit();
		
	}
	
	
	
	@Test
		
	public void testLardeur () {
		
		WebDriverWait wait = new WebDriverWait(driver, 10); 
		
		driver.get("https://fr.wowhead.com/");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='onetrust-accept-btn-handler']")));
		
		//accepter les cookies
		driver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click();
		
		System.out.println("Vous avez accepté les cookies");
	
		
		//rechercherLardeur
		WebElement champRecherche = driver.findElement(By.xpath("//*[@name='q']"));
		champRecherche.click();
		champRecherche.clear();
		champRecherche.sendKeys("lardeur");
		champRecherche.sendKeys(Keys.ENTER);
		
		System.out.println("La recherche LARDEUR a été lancée");
		
		//Attendre le chargement de la page
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='listview-cleartext'][@href='/npc=46254/lardeur']")));
		
		//Vérifier qu'on est bien sur la page "Lardeur"
		WebElement resultLardeur = driver.findElement(By.xpath("//*[@class='listview-cleartext'][@href='/npc=46254/lardeur']")); 
		assertEquals ("Tu t'es trompé !","Lardeur", resultLardeur.getText());
		System.out.println("Le personnage Lardeur a bien été sélectionné");
		
		//sélectionner le bon lardeur dans la liste
		driver.findElement(By.xpath("//*[@class='listview-cleartext'][@href='/npc=46254/lardeur']")).click();	
	
		System.out.println("Le lardeur avec tête de mort a été sélectionné");	
		
		//Sélectionner le chahuteur de cadavre
		driver.findElement(By.xpath("//a[@href=\"/item=2168/chahuteurs-de-cadavre\"]")).click();
		WebElement titreChahuteur = driver.findElement(By.xpath("//h1"));
		
		assertEquals ("Oups", titreChahuteur.getText(), "Chahuteurs de cadavre");
		System.out.println("Le chahuteur de cadavre a bien été selectionné");
		
		//Vérifier les objets 1: chahuteur de cadavres
		
		List<WebElement> listeObjets1 = driver.findElements(By.xpath("//div[@class][@id]/table//span"));
		
		ArrayList<String> listeCaractSite1 = new ArrayList<String>();
		ArrayList<String> listeCaracAttendue1 = new ArrayList<String>();
		
		for (WebElement element : listeObjets1) {
			listeCaractSite1.add(element.getText());
		}
				
		listeCaracAttendue1.add("Niveau d'objet 57");
		listeCaracAttendue1.add("Tissu");
		listeCaracAttendue1.add("Armure : 2");
		listeCaracAttendue1.add("+12 Intelligence");
		listeCaracAttendue1.add("+18 Endurance");
		listeCaracAttendue1.add("Augmente votre score de hâte de +13");
		listeCaracAttendue1.add("+10 Versatilité");
		listeCaracAttendue1.add("36");
		listeCaracAttendue1.add("76");
		listeCaracAttendue1.add("39");
		
		assertEquals("Les caractéristiques du chahuteur de cadavres sont incorrectes",listeCaracAttendue1, listeCaractSite1);
		
		System.out.println("Les caractéristiques du chahuteur de cadavres sont correctes");
			
		//Vérifier les objets 2: chausses de Lardeur
		//Sélectionner les chausses
		
		driver.navigate().back();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='q3 listview-cleartext'][@href='/item=1934/chausses-de-lardeur']")));
		driver.findElement(By.xpath("//*[@class='q3 listview-cleartext'][@href='/item=1934/chausses-de-lardeur']")).click();
		
		WebElement titreChausse = driver.findElement(By.xpath("//h1"));
		titreChausse.getText();
		
		assertEquals("Mauvaise page","Chausses de Lardeur", titreChausse.getText());
		System.out.println("Les chausses de Lardeur ont bien été selectionnées");
		
		//Vérifier les objets via le tableau
		
		List<WebElement> listeObjets2 = driver.findElements(By.xpath("//div[@class][@id]/table//span"));
		
		ArrayList<String> listeCaractSite2 = new ArrayList<String>();
		ArrayList<String> listeCaracAttendue2 = new ArrayList<String>();
		
		for (WebElement element : listeObjets2) {
			listeCaractSite2.add(element.getText());
		}
				
		listeCaracAttendue2.add("Niveau d'objet 57");
		listeCaracAttendue2.add("Cuir");
		listeCaracAttendue2.add("Armure : 6");
		listeCaracAttendue2.add("+16 [Agilité or Intelligence]");
		listeCaracAttendue2.add("+24 Endurance");
		listeCaracAttendue2.add("Augmente votre score de coup critique de +21");
		listeCaracAttendue2.add("+12 Versatilité");
		listeCaracAttendue2.add("52");
		listeCaracAttendue2.add("73");
		listeCaracAttendue2.add("92");
		
		assertEquals("Les caractéristiques des chausses de Lardeur sont incorrectes",listeCaracAttendue2, listeCaractSite2);
		
		System.out.println("Les caractéristiques des chausses de Lardeur sont correctes");
		
	}
	}


 