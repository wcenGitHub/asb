# asb

Due to the limited of time, I only briefly wrote a few unit tests and UI automation tests, will add more if there is more time.

UI automation tests may fail, please run again if it does.

I have set up dagger for dependency injection, which includes two scopes, singleton and session. In real app code, session scope should be cleared and recreated if the session is killed such as user logout.