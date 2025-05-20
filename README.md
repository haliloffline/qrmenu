# QR Menü Sistemi

Restoranlara özel olarak geliştirilen QR Menü Sistemi; masa tanımlama, garson çağırma, masa istatistikleri, anlık menü yönetimi ve çok daha fazlasını tek bir sistemde birleştirir.

## Teknolojiler

- Backend: Spring Boot (Java 21)
- Frontend: React (henüz başlanmadı)
- Veritabanı: MySQL
- API Testi: Postman
- ORM: Spring Data JPA + Hibernate
- Güvenlik: Spring Security (JWT entegrasyonu eklenecek)

## Proje Yapısı

```
qrmenu-backend/
├── controller/
├── dto/
├── entity/
├── repository/
├── service/
├── config/
└── util/
```

## Özellikler

- Her masaya özel QR kod ve tanımlama
- Garson çağırma sistemi
- Masa bazlı kullanım ve çağrı analizleri
- Tarih aralığına göre filtreli istatistik
- Yorum ve geri bildirim altyapısı (devre dışı bırakıldı)
- Çoklu dil desteği (JSON yapısıyla destekleniyor)
- Kullanıcı giriş sistemi (yakında eklenecek)

## Kurulum

```bash
git clone https://github.com/kullaniciadi/qrmenu-backend.git
cd qrmenu-backend
```

MySQL'de veritabanınızı oluşturun ve `application.properties` içinde bağlantı bilgilerinizi girin.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/qrmenu
spring.datasource.username=root
spring.datasource.password=123456
```

## Test

Tüm API’ler Postman ile test edilmiştir.  
`/api/restaurants`, `/api/tables`, `/api/call-requests`, `/api/analytics/...` uç noktalarını kullanarak test yapılabilir.

## Bilgilendirme

- Bu proje henüz geliştirme aşamasındadır.
- Frontend tarafı (React) henüz başlamamıştır.
- Yorum/geri bildirim modülü devre dışı bırakılmıştır.

## Yol Haritası

- [ ] Kullanıcı giriş sistemi (JWT ile)
- [ ] Menü düzenleme ve stok kontrol sistemi
- [ ] Yönetici paneli React ile
- [ ] Mobil uyumlu frontend arayüz

## Lisans

Bu proje henüz lisanslanmamıştır.