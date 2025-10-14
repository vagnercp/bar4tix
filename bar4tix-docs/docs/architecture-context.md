flowchart LR
  %% --- CONTEXTO (alto nível) ---

  subgraph USERS[Users]
    U1[Consumidor App · Web]
    U2[Lojista · Analista (BI)]
  end

  U1 -->|QR NFC-e · Busca| GW[API Gateway]
  U2 -->|Dashboards · Export| GW

  GW --> BFF[Comparison · BFF]
  GW --> BI[BI Service]
  GW --> ID[Identity]

  BFF --> PRC[Pricing Service]
  BFF --> CAT[Catalog Service]
  BFF --> PLC[Places Service]

  subgraph DATA[Data & Storage]
    DB[(PostgreSQL)]
    RD[(Redis Cache)]
    KF[(Kafka Topics)]
    OBJ[(Object Storage)]
  end

  INJ[Ingestion] --> KF
  OCR[OCR · Crawler] --> INJ
  INJ --> CAT
  INJ --> PRC

  CAT --> DB
  PRC --> DB
  PRC --> RD
  BI --> DB
  BI --> OBJ

  %% estilos simples compatíveis com GitHub
  classDef comp fill:#f6f8fa,stroke:#999,color:#111;
  class GW,BFF,BI,ID,PRC,CAT,PLC,INJ,OCR comp;
  class DB,RD,KF,OBJ comp;
