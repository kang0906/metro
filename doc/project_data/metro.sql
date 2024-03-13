show databases;

use metro;

show tables;

select * from arrival_info_log;
select * from seoul_api_station_info;
SELECT * from seoul_api_request_log;

select * from arrival_info_log where created_date_time > now();
select * from arrival_info_log where arvl_msg2 != '미설정';
select count(*), line_name, '[[0-9]]번째 전역' from arrival_info_log where arvl_msg2 != '미설정' AND arvl_msg2 REGEXP '[[0-9]]번째 전역' group By line_name ;	// 응답 형식 통계
select * from arrival_info_log where arvl_msg2 != '미설정' AND line_name = '7호선';
select * from arrival_info_log where arvl_msg2 = '' OR arvl_msg2 is null ;

select count(*) from arrival_info_log;
select count(*), a.line_name from arrival_info_log a where arrival_time > 0 group by line_name UNION select count(*), "total" from arrival_info_log where arrival_time > 0;
select count(*), a.line_name  from arrival_info_log a where arrival_time = 0 group by line_name UNION select count(*), "total" from arrival_info_log where arrival_time = 0;

-- delete from arrival_info_log where line_name = '1호선';
select count(*), station_name  from arrival_info_log where line_name = '1호선' group by station_name;
-- ================== 지하철 역, 호선 코드 정보 ================================

SELECT count(*), subway_name from seoul_api_station_info group by subway_name ;
SELECT count(*) from seoul_api_station_info;

select * from seoul_api_station_info;

select * from seoul_api_station_info where station_name = '운천';
select * from seoul_api_station_info where station_id  = 1063075337;
select * from seoul_api_station_info where station_name = '강일';

select * from seoul_api_station_info where subway_name  = '우이신설선';
select * from seoul_api_station_info where subway_name  = '경의중앙선';
select * from seoul_api_station_info where subway_name  = '5호선';
select * from seoul_api_station_info where subway_name  = '1호선';
-- INSERT 예시
INSERT INTO metro.seoul_api_station_info
(station_id, station_name, subway_id, subway_name)
VALUES('역 코드', '역 이름', '호선 코드', '호선 이름');

INSERT INTO seoul_api_station_info(subway_id,station_id,station_name,subway_name) VALUES (1092,1092004701,'북한산우이','우이신설선');

-- ========================================================================


-- ================== 호선별 도착정보 메세지 형식(포멧) 통계 ================================
-- alter table arrival_info_log ADD COLUMN arvl_msg2_format varchar(255);

select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time, arvl_msg2_format from arrival_info_log 
	where arvl_msg2 != '미설정' group by arvl_msg2 ;

select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time  from arrival_info_log 
	where arvl_msg2 != '미설정' and arvl_msg2 REGEXP '[가-힇()] 도착';

select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time  from arrival_info_log 
	where arvl_msg2 != '미설정' and arvl_msg2 REGEXP '^도착$';

select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time  from arrival_info_log 
	where arvl_msg2 != '미설정' and arvl_msg2 REGEXP '[[0-9]]번째 전역';	-- 이 패턴의 경우 '[가-힇] 도착' 케이스와 통계 정보가 겹침

select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time  from arrival_info_log 
	where arvl_msg2 != '미설정' and arvl_msg2 REGEXP '[가-힇()] 진입';-- and line_name = '2호선';

select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time  from arrival_info_log 
	where arvl_msg2 != '미설정' and arvl_msg2 REGEXP '[가-힇()] 출발';-- and line_name = '2호선';

select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time  from arrival_info_log 
	where arvl_msg2 != '미설정' and arvl_msg2 REGEXP '[가-힇()] 전역출발';-- and line_name = '2호선';

select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time  from arrival_info_log 
	where arvl_msg2 != '미설정' and arvl_msg2 REGEXP '[0-9]{1,2}분 후';-- and line_name = '2호선'; -- 이 패턴의 경우 (X분 후) 뒤에 역이릉이 없는 경우가 존재함
	
select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time  from arrival_info_log 
	where arvl_msg2 != '미설정' and arvl_msg2 REGEXP '[0-9]{1,2}분 [0-9]{1,2}초 후';-- and line_name = '2호선'; -- 이 패턴의 경우 (X분 후) 뒤에 역이릉이 없는 경우가 존재함

select count(*), line_name, 'X번째 전역' from arrival_info_log where arvl_msg2 != '미설정' AND arvl_msg2 REGEXP '[[0-9]]번째 전역' group By line_name ;
select count(*), line_name, 'XX 도착' from arrival_info_log where arvl_msg2 != '미설정' AND arvl_msg2 REGEXP '[가-힇()] 도착' group By line_name ;
select count(*), line_name, '도착' from arrival_info_log where arvl_msg2 != '미설정' AND arvl_msg2 REGEXP '^도착$' group By line_name ;
select count(*), line_name, '전역 도착' from arrival_info_log where arvl_msg2 != '미설정' AND arvl_msg2 REGEXP '전역 도착' group By line_name ; -- 이 패턴의 경우 '[가-힇] 도착' 케이스와 통계 정보가 겹침
select count(*), line_name, 'XX 진입' from arrival_info_log where arvl_msg2 != '미설정' AND arvl_msg2 REGEXP '[가-힇()] 진입' group By line_name ;
select count(*), line_name, 'XX 출발' from arrival_info_log where arvl_msg2 != '미설정' AND arvl_msg2 REGEXP '[가-힇()] 출발' group By line_name ;
select count(*), line_name, 'XX 전역출발' from arrival_info_log where arvl_msg2 != '미설정' AND arvl_msg2 REGEXP '[가-힇()] 전역출발' group By line_name ;
select count(*), line_name, 'X분 후' from arrival_info_log where arvl_msg2 != '미설정' AND arvl_msg2 REGEXP '[0-9]{1,2}분 후' group By line_name ;
select count(*), line_name, 'X분 후' from arrival_info_log where arvl_msg2 != '미설정' AND arvl_msg2 REGEXP '[0-9]분 후' group By line_name ;
select count(*), line_name, 'X분 X초 후' from arrival_info_log where arvl_msg2 != '미설정' AND arvl_msg2 REGEXP '[0-9]{1,2}분 [0-9]{1,2}초 후' group By line_name ;

-- ## 포맷 형식 저장
UPDATE arrival_info_log SET arvl_msg2_format = 'XX 전역출발' where arvl_msg2 REGEXP '[가-힇()] 전역출발';

select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time, arvl_msg2_format from arrival_info_log 
	where arvl_msg2_format = 'X번째 전역';

select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time, arvl_msg2_format from arrival_info_log 
	where arvl_msg2_format = 'XX 도착';

select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time, arvl_msg2_format from arrival_info_log 
	where arvl_msg2_format = '도착';

select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time, arvl_msg2_format from arrival_info_log 
	where arvl_msg2_format = 'XX 진입';

select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time, arvl_msg2_format from arrival_info_log 
	where arvl_msg2_format = 'XX 출발';

select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time, arvl_msg2_format from arrival_info_log 
	where arvl_msg2_format = 'XX 전역출발';

select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time, arvl_msg2_format from arrival_info_log 
	where arvl_msg2_format = 'X분 후';

select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time, arvl_msg2_format from arrival_info_log 
	where arvl_msg2_format = 'X분 X초 후';

-- ============ group by =============
select count(*), line_name, arvl_msg2_format from arrival_info_log 
	where arvl_msg2_format = 'X번째 전역' group By line_name;

select count(*), line_name, arvl_msg2_format from arrival_info_log 
	where arvl_msg2_format = 'XX 도착' group By line_name;

select count(*), line_name, arvl_msg2_format from arrival_info_log 
	where arvl_msg2_format = '도착' group By line_name;

select count(*), line_name, arvl_msg2_format from arrival_info_log 
	where arvl_msg2_format = 'XX 진입' group By line_name;

select count(*), line_name, arvl_msg2_format from arrival_info_log 
	where arvl_msg2_format = 'XX 출발' group By line_name;

select count(*), line_name, arvl_msg2_format from arrival_info_log 
	where arvl_msg2_format = 'XX 전역출발' group By line_name;

select count(*), line_name, arvl_msg2_format from arrival_info_log 
	where arvl_msg2_format = 'X분 후' group By line_name;

select count(*), line_name, arvl_msg2_format from arrival_info_log 
	where arvl_msg2_format = 'X분 X초 후' group By line_name;

-- 저장되지 못한 케이스 있는지 확인 쿼리
select arrival_time, line_name , arvl_msg2, arvl_msg3, station_name, next_station_name, created_date_time, arvl_msg2_format from arrival_info_log 
	where arvl_msg2 != '미설정' and arvl_msg2_format is NULL;
-- ========================================================================

