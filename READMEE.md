# 기능 목록

- RandomCoinGenerator
- [ ] : 금액에 맞는 동전으로 랜덤 생성

- Product
- [ ] : 상품 이름, 가격을 갖는 객체

- SaleProduct
- [ ] : Product, 수량을 갖는 객체

- SaleList
- [ ] : List<SaleProduct> 형태

- VendingMachine
- [ ] : SaleList, 보유하고 있는 금액으로 생성
- [ ] : SaleList 업데이트
- [ ] : 잔돈 계산

- AmountFormatValidator
- [ ] : 숫자인지 확인

- AmountBusinessValidator
- [ ] : 0 이상인지 확인

- ProductFormatValidator
- [ ] : [이름,가격,수량];[이름,가격,수량] 형식 확인
- [ ] : 가격이 숫자인지 확인
- [ ] : 수량이 숫자인지 확인

- ProductBusinessValidator
- [ ] : 가격이 100 이상인지 확인
- [ ] : 가격이 10원으로 나누어 떨어지는지 확인
- [ ] : 수량이 1 이상인지 확인
- [ ] : 이미 존재하는 상품인지 확인
