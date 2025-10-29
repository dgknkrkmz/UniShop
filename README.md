# 🛒 UniShop | Android E-Ticaret Uygulaması (Bootcamp Bitirme Projesi)

Bu proje, bir Android Bootcamp'in bitirme projesi olarak **Kotlin** programlama dili ve modern Android geliştirme araçları kullanılarak geliştirilmiş bir **mobil E-Ticaret uygulamasıdır**.  
Ürün listeleme, detay görüntüleme, arama ve sepet yönetimi gibi E-Ticaret fonksiyonlarını barındırır.

---

## ✨ Uygulama Özellikleri (Detaylı Görsel Sunum)

---

### 🏠 1. Ürün Listeleme 

**Kategori:** Ürün  

Ana sayfada tüm ürünler, kategorisine bakılmaksızın listelenir.  
Kullanıcılar ürün kartlarına tıklayarak detay sayfasına geçebilir.

<img src="UniShop/assets/ana_sayfa-portrait.png" alt="Adet Seçimi.png" width="400" height="700"/>

---

### 🔍 2. Canlı Arama

**Kategori:** Arama  

Kullanıcı arama kutusuna yazı yazmaya başladığı anda, **marka** veya **ürün adına** göre filtreleme anlık olarak yapılır ve sonuçlar kullanıcıya dinamik olarak sunulur.

<img src="UniShop/assets/canlı_arama-portrait.png" alt="Adet Seçimi" width="400" height="700"/>

---

### 📄 3. Ürün Detay Sayfası

**Kategori:** Detay  

Seçilen ürünün resmi, tam fiyatı, markası ve kategorisi büyük bir ekranda gösterilir.  
Bu ekran, **sepete ekleme işlemi** için bir merkez görevi görür.

<img src="UniShop/assets/ürün_detay-portrait.png" alt="Adet Seçimi" width="400" height="700"/>

---

### ➕ 4. Adet Seçimi

**Kategori:** Sepet  

Ürün detay sayfasında, kullanıcı sepete eklemeden önce istediği sipariş adedini pratik bir şekilde artırıp azaltabilir.  
Toplam fiyat, seçilen adete göre anlık güncellenir.

<img src="UniShop/assets/adet_seçimi-portrait.png" alt="Adet Seçimi" width="400" height="700"/>

---

### 🧾 6. Sepet Görüntüleme

**Kategori:** Sepet  

Ana sayfadaki sepet ikonuna tıklandığında, kullanıcının sepetteki tüm ürünleri listelenir.  
Bu ekranda ürünlerin adedi, fiyatları ve tüm sepetin toplam tutarı net bir şekilde gösterilir.

<img src="UniShop/assets/sepet-portrait.png" alt="Adet Seçimi" width="400" height="700"/>

---

### ❌ 7. Sepetten Silme

**Kategori:** Sepet  

Kullanıcı, sepetteki herhangi bir ürünün yanındaki silme butonuna tıklayarak (API'ye gönderimi ile) ürünü sepetinden kalıcı olarak kaldırabilir.  
Liste ve toplam fiyat anlık olarak güncellenir.

<img src="UniShop/assets/sepet_urun_silme-portrait.png" alt="Adet Seçimi" width="400" height="700"/>

---

## 🛠️ Kullanılan Teknolojiler

Bu proje, modern Android mimarisinin en modern pratiklerini takip etmek için aşağıdaki bileşenleri kullanır:

- **Programlama Dili:** Kotlin  
- **Arayüz:** Jetpack Compose (Modern, deklaratif UI)  
- **Mimarî:** MVVM (Model-View-ViewModel)  
- **Bağımlılık Yönetimi (DI):** Hilt (Dagger Hilt)  
- **Ağ İşlemleri:** Retrofit & GSON (REST API ile hızlı iletişim)  
- **Asenkron İşlemler:** Kotlin Coroutines & Flow (Veri akışını yönetmek için)  
- **Resim Yükleme:** Coil (URL'den resimleri hızlıca yüklemek ve önbelleğe almak için)  
- **Navigasyon:** Jetpack Compose Navigation

---

## ⚙️ Proje Mimarisi

Uygulama, **temiz kod prensiplerini** ve **modülerliği** sağlamak için Katmanlı Mimari (Layered Architecture) kullanılarak tasarlanmıştır.

### 📂 Data Katmanı

- **Entity:** `Product.kt`, `CartProduct.kt`  
  (API'den gelen JSON verilerini karşılayan veri modelleri)  
- **DataSource:** `ProductService.kt`  
  (Retrofit arayüzü ile API uç noktalarını tanımlar)

### 🧠 Domain (Repository) Katmanı

- **Repository:** `ProductRepository.kt`  
  (Veri kaynakları (API) ile ViewModel'ler arasında köprü görevi görür, iş mantığını soyutlar.)

### 🎨 Presentation (UI/Logic) Katmanı

- **ViewModel:** `HomeViewModel.kt`, `CartViewModel.kt`  
  (Veriyi hazırlar ve iş mantığını yönetir.)  
- **UI:** `AppNavigation.kt`, `HomeScreen.kt`, `DetailScreen.kt`, `CartScreen.kt`  
  (Kullanıcı arayüzü bileşenleri)

---

## 📸 Uygulama Ekran Görüntüleri (Özet)

1. **Ana Sayfa (Home Screen)**  
   <img src="UniShop/assets/ana_sayfa-portrait.png" alt="Adet Seçimi" width="300" height="500"/>

2. **Ürün Detay Sayfası (Detail Screen)**  
   <img src="UniShop/assets/ürün_detay-portrait.png" alt="Adet Seçimi" width="300" height="500"/>

3. **Sepet Sayfası (Cart Screen)**  
   <img src="UniShop/assets/sepet-portrait.png" alt="Adet Seçimi" width="300" height="500"/>

---

## 🚀 Kurulum, Teşekkür ve İletişim

**Kurulum:**  
```bash
git clone https://github.com/kullanici/UniShop.git
cd UniShop
./gradlew build
```

**🙏 Teşekkür ve 📬 İletişim:**  

Bu projenin geliştirilme sürecindeki rehberliği, bilgisi ve motivasyonu için değerli Kasım Adalan hocama teşekkürlerimi sunarım.  

Proje hakkında herhangi bir sorunuz veya geri bildiriminiz varsa aşağıdaki kanallardan benimle iletişime geçebilirsiniz:

**📧 E-posta:** korkmazdogu09@gmail.com  
**🔗 LinkedIn:** www.linkedin.com/in/-dogukankorkmaz

