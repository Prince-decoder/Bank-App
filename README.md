├── app/
│   ├── MainActivity.kt          # Handles registration, device binding, and transactions
│   ├── AuthManager.kt           # OTP & biometric verification logic
│   ├── MLInference.kt           # Runs local anomaly detection (TFLite)
│   ├── Dashboard.kt             # Displays recent transactions and anomalies
│   └── utils/
│       ├── encryption.kt        # Handles keypair generation and signing
│       ├── network.kt           # Backend communication
│       └── logger.kt            # Local and cloud logging
│
├── server/
│   ├── app.py                   # FastAPI/Flask backend
│   ├── models/
│   │   └── anomaly_detector.pkl # Trained fraud detection model
│   ├── services/
│   │   ├── auth_service.py      # Token, JWT, and MFA validation
│   │   ├── txn_service.py       # Transaction handling and feature extraction
│   │   └── alert_service.py     # Sends fraud alerts
│   └── db/
│       └── users.db / logs.db   # Data persistence
│
├── notebooks/
│   ├── model_training.ipynb     # Fraud pattern training
│   └── evaluation.ipynb         # Model testing and performance
│
├── assets/
│   ├── autoencoder.tflite       # TFLite model for local inference
│   ├── thresholds.json          # Risk thresholds
│   └── scaler.json              # Normalization parameters
│
└── README.md
