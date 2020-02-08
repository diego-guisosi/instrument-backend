DROP TABLE IF EXISTS tbl_instr;
DROP TABLE IF EXISTS tbl_hist_price;


CREATE TABLE tbl_instr (
   instr_id INT NOT NULL,
   instr_symbol VARCHAR(20) NOT NULL,
   instr_mat_date DATE NOT NULL
);

ALTER TABLE tbl_instr ADD CONSTRAINT tbl_instr_pk PRIMARY KEY(instr_id);


CREATE TABLE tbl_hist_price (
   instr_id INT NOT NULL,
   hist_price_date DATE NOT NULL,
   hist_price_value NUMBER NOT NULL
);

ALTER TABLE tbl_hist_price ADD CONSTRAINT tbl_hist_price_pk PRIMARY KEY(instr_id, hist_price_date);
ALTER TABLE tbl_hist_price ADD FOREIGN KEY (instr_id) REFERENCES tbl_instr(instr_id);