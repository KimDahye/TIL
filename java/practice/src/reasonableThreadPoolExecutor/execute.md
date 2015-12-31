 ```java
    public void execute(Runnable command) {
        if (command == null)
            throw new NullPointerException();

        int c = ctl.get();
        if (workerCountOf(c) < corePoolSize) {
            //core 보다 작을 때
            if (addWorker(command, true))
                return;
            c = ctl.get();
        }
        else if (workerCountOf(c) < maxPoolSize) {
            // core보다 크고 max보다 작을 때
            if(addWorker(command, false))
                return;
            c = ctl.get();
        }
        if (isRunning(c) && workQueue.offer(command)) {
            // 돌아가는 worker가 max 초과이고
            // executor running상태임
            // 큐에 offer 가 성공했을 때
            int recheck = ctl.get();
            if (! isRunning(recheck) && remove(command))
                reject(command);
            else if (workerCountOf(recheck) == 0)
                addWorker(null, false);
        }
        else
            //돌아가는 worker가 max 초과이고
            //executor는 Running상태가 아니거나 큐에 offer가 실패했을 때
            reject(command);
    }
 ```