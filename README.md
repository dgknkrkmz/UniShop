# 🛒 UniShop | Android E-Ticaret Uygulaması (Bootcamp Bitirme Projesi)

![Kotlin Badge](https://img.shields.io/badge/Kotlin-0095D5?style=flat-square) ![Compose Badge](https://img.shields.io/badge/Jetpack%20Compose-20232A?style=flat-square) ![MVVM Badge](https://img.shields.io/badge/MVVM-Architecture-blue?style=flat-square)

Bu proje, bir Android Bootcamp'in bitirme projesi olarak **Kotlin** ve modern Android araçlarıyla geliştirilmiş tam teşekküllü bir mobil e-ticaret uygulamasıdır.  
(Uygulama: Ürün listeleme, detay, canlı arama, sepet yönetimi vb.)

---

## ✨ Öne Çıkan Özellikler

### 🏠 Ürün Listeleme (Home Screen)
Tüm ürünler ana sayfada listelenir. Kullanıcı ürün kartına tıklayarak detay sayfasına gider.

### 🔍 Canlı Arama
Arama kutusuna yazdığın anda marka veya ürün adına göre **anlık filtreleme** yapılır.

### 📄 Ürün Detay Sayfası
Seçilen ürünün resmi, tam fiyatı, markası ve kategorisi büyük bir ekranda gösterilir. Sepete ekleme burada gerçekleşir.

### ➕ Adet Seçimi
Detay ekranında adet artır/azalt fonksiyonu; toplam fiyat anlık güncellenir.

### 🛍️ Sepete Ekleme
Seçili adetle birlikte `POST` ile API’ye kaydeder; işlem sonrası kullanıcı yönlendirmesi yapılır.

### 🧾 Sepet Görüntüleme
Sepet ikonu ile sepetteki ürünler listelenir; toplam tutar ve adet gösterilir.

### ❌ Sepetten Silme
Her ürünü `sepetId` göndererek API üzerinden silebilirsin; liste ve toplam anında güncellenir.

---

## 🛠️ Kullanılan Teknolojiler

- **Programlama Dili:** Kotlin  
- **Arayüz:** Jetpack Compose  
- **Mimarî:** MVVM  
- **DI:** Hilt (Dagger Hilt)  
- **Ağ:** Retrofit + GSON  
- **Asenkron:** Coroutines & Flow  
- **Resim Yükleme:** Coil  
- **Navigasyon:** Jetpack Compose Navigation

---

## ⚙️ Proje Mimarisi

### Data Katmanı
- `Product.kt`, `CartProduct.kt` (Entity)  
- `ProductService.kt` (Retrofit arayüzü)

### Domain / Repository
- `ProductRepository.kt`

### Presentation (UI / Logic)
- `HomeViewModel.kt`, `CartViewModel.kt`  
- `AppNavigation.kt`, `HomeScreen.kt`, `DetailScreen.kt`, `CartScreen.kt`

---

## 📸 Ekran Görüntüleri

> Yer tutucu — kendi ekran görüntülerini `assets/screens/` altına koyup aşağıdaki gibi çağırabilirsin.

![Home Screen](./assets/screens/home.png)  
![Detail Screen](./assets/screens/detail.png)  
![Cart Screen](./assets/screens/cart.png)

---

## 🚀 Kurulum ve Çalıştırma

```bash
git clone https://github.com/KULLANICI/REPO.git
# Android Studio ile projeyi aç
# Gradle sync -> Run on emulator / device
