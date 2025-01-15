export interface User {
  id: string;
  email: string;
  subscriptionPlan: SubscriptionPlan;
}

export enum SubscriptionPlan {
  FREE = "FREE",
  BASIC = "basic",
  PREMIUM = "PREMIUM",
  ENTERPRISE = "enterprise",
}
