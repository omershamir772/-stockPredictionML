# Project: stockPredictionML

## 1. Goal
The model uses previous data to predict if the stock will go up or down

Example:
Predict if stock price will go up or down.

Input: open value, close value, high value, low value, volume, timestamp, prediction time
Output: if the stock value will go up or down

---

## 2. Data
Where data comes from and what it looks like.

Source:
- API - yahoo finance api

Format:
- JSON

Features:
- openArr
- highArr
- lowArr
- closeArr
- volumeArr
- timestampArr
- prediction time

---

## 3. Data Pipeline
Steps before training.

1. Load data from yahoo finance api
2. Clean data (remove nulls)
3. Organize data
4. Run forward propagation

Flow:
API → Clean → Organize → Dataset

---

## 4. Model
What model you use.

Type:
- Neural network / regression

Structure:
- Input: 7 features
- Hidden layers: 2 layers (16 neurons each)
- Output: 2 neurons

Loss:
- MSE

Optimizer:
- BGD - batch gradient descent

---

## 5. Training
How you train.

Learning rate: 0.01

Process:
Data → Model → Loss → Backprop → Update weights

---

## 6. Evaluation
How you check if it works.

Metrics:
- accuracy / loss

Method:
- test dataset

---

## 7. Inference (using the model)
How you use it after training.

Input:
- new data

Output:
- prediction

Example:
API → Model → Result

---

## 8. Project Structure
the program is divided into : four coding file
- Main.java - run the program
- api.java - read from yahoo finance api
- data.java - organize the data
- neuralNetwork.java - forward propagation and back propagation
~~~~