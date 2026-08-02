CREATE TABLE ledger (
      id BIGSERIAL PRIMARY KEY,
      type VARCHAR(10) NOT NULL,        -- INCOME / EXPENSE
      category VARCHAR(30) NOT NULL,    -- 식비, 교통비, 월급 등
      amount INTEGER NOT NULL,
      memo VARCHAR(200),
      record_date DATE NOT NULL
);