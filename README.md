# E-commerce Backend (Spring Boot)

## Lancer
- `mvn spring-boot:run`
- H2 console: `/h2` (JDBC URL `jdbc:h2:mem:shopdb`)

## Endpoints clés
- Categories: `POST/PUT/DELETE /api/categories`, lier/délier produit: `POST/DELETE /api/categories/{cid}/products/{pid}`
- Products: `POST/PUT/DELETE /api/products`
- Carts: `POST /api/carts`, `POST/PUT /api/carts/{id}/items`, `DELETE /api/carts/{id}/items/{productId}`, `GET /api/carts/{id}`

## Modèle
- Category (hiérarchie), Product (name, price, stock), ManyToMany.
- Cart + CartItem (quantité) avec total calculé.
