import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import io
import requests
from datetime import datetime
import os
import pymysql

# 각 lot_id의 총 주차 구역 수 정의
TOTAL_SLOTS = {
    "A": 41,
    "B": 31,
    "C": 11,
}

# 1. 주차 데이터 가져오기
def fetch_data_from_db(lot_id):
    connection = pymysql.connect(
        host='localhost',
        user='dbid233',
        password='dbpass233',
        database='db24310'
    )
    try:
        # 쿼리 실행
        query = f"SELECT * FROM T2_PARKING_STATISTICS WHERE lot_id = '{lot_id}'"
        df = pd.read_sql(query, connection)
    finally:
        connection.close()

    return df

# 2. 시각화 함수 (그래프를 이미지로 반환)
def plot_parking_data_to_image(df, lot_id):
    unique_dates = df['date'].unique()

    total_slots = TOTAL_SLOTS.get(lot_id, 50)
    fixed_time_slots = [f"{hour:02d}:00" for hour in range(7, 19)]

    rows, cols = 3, 3
    fig, axes = plt.subplots(rows, cols, figsize=(15, 12), sharey=True)

    axes = axes.flatten()

    for i, date in enumerate(unique_dates):
        daily_data = df[df['date'] == date]

        daily_data['time'] = pd.to_datetime(daily_data['time'], format='%H:%M').dt.strftime('%H:%M')        
        time_group = daily_data.groupby('time')[['num_occupied', 'num_available']].sum()
        
        time_group = time_group.reindex(fixed_time_slots, fill_value=0)

        bars1 = axes[i].bar(time_group.index, time_group["num_occupied"], label="Occupied", color="salmon")
        bars2 = axes[i].bar(time_group.index, time_group["num_available"], bottom=time_group["num_occupied"], label="Available", color="skyblue")

        axes[i].set_title(f"Date: {date}")
        axes[i].set_xlabel("Time", fontsize=10)
        axes[i].set_xticks(fixed_time_slots[::2]) 
        axes[i].tick_params(axis='x', rotation=0)

        axes[i].set_ylim(0, total_slots)
        axes[i].yaxis.set_major_locator(plt.MaxNLocator(integer=True))
        axes[i].yaxis.grid(True, linestyle='--', linewidth=0.7, alpha=0.7)

        for bar in bars1:
            height = bar.get_height()
            if height > 0:
                axes[i].text(bar.get_x() + bar.get_width() / 2, height / 2, f"{int(height)}", ha="center", va="center", color="white", fontsize=9)
        for bar in bars2:
            height = bar.get_height()
            bottom = bar.get_y()
            if height > 0:
                axes[i].text(bar.get_x() + bar.get_width() / 2, bottom + height / 2, f"{int(height)}", ha="center", va="center", color="white", fontsize=9)

    for j in range(len(unique_dates), rows * cols):
        axes[j].axis("off")

    axes[0].set_ylabel("Number of Spaces")
    fig.legend(["Occupied", "Available"], loc='upper center', ncol=2)
    fig.subplots_adjust(hspace=0.5)
    
    # 이미지를 바이트로 변환
    buf = io.BytesIO()
    plt.savefig(buf, format='png')
    buf.seek(0)
    return buf

# 3. 서버로 이미지 전송
def send_image_to_server(image_data, lot_id, save_directory='src/main/resources/static/'):
    if not os.path.exists(save_directory):
        os.makedirs(save_directory)

    # 저장할 파일 이름 생성
    file_name = f"{lot_id}_statistics.png"
    file_path = os.path.join(save_directory, file_name)

    # BytesIO 객체에서 데이터를 추출하여 저장
    if hasattr(image_data, 'getvalue'):  # image_data가 BytesIO 객체인지 확인
        image_data = image_data.getvalue()

    # 이미지 데이터를 파일로 저장
    with open(file_path, 'wb') as file:
        file.write(image_data)

    print(f"Image saved locally at: {file_path}")

# 4. 데이터 요청 및 시각화, 서버로 전송
def fetch_data_and_send_to_server(lot_id):
    print(f"Fetching data for lot: {lot_id}")
    # 주차 데이터 가져오기
    df = fetch_data_from_db(lot_id)
    
    # 그래프 생성
    image_data = plot_parking_data_to_image(df, lot_id)
    
    # 서버로 전송
    send_image_to_server(image_data, lot_id)

fetch_data_and_send_to_server("A")
fetch_data_and_send_to_server("B")
fetch_data_and_send_to_server("C")
