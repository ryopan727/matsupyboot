# matsupyboot

springbootのよくあるAPI基盤実装

## 概要

- SpringbootによるAPI機能
- mybatis generatorを活用したentity mapper自動生成
- 外部API連携★未実装

## ターゲット構成

- OpenJDK 21
- Springboot 3.5.7
- API機能のみ（画面なし）
- DBはpostgresql
- json形式によるreq/res

## 内部構成

- matsuypapi: API機能本体
- matsupycmn：共通処理

・・・今後増える？かも

### レスポンス共通化設計

#### 正常

```
{
    "result": "0",
    "data": {
        ★各APIによるレスポンス設定
    }
}
```

#### 異常

```
{
    "result": "1",
    "errInfo": [
        "errCd": "1",
        "message": "エラー。"
    ]
}
```