```java
    public void execute(Runnable command) {
        if (command == null)
            throw new NullPointerException();
        /*
         * Proceed in 3 steps:
         *
         * 1. If fewer than corePoolSize threads are running, try to
         * start a new thread with the given command as its first
         * task.  The call to addWorker atomically checks runState and
         * workerCount, and so prevents false alarms that would add
         * threads when it shouldn't, by returning false.
         *
         * 2. If a task can be successfully queued, then we still need
         * to double-check whether we should have added a thread
         * (because existing ones died since last checking) or that
         * the pool shut down since entry into this method. So we
         * recheck state and if necessary roll back the enqueuing if
         * stopped, or start a new thread if there are none.
         *
         * 3. If we cannot queue task, then we try to add a new
         * thread.  If it fails, we know we are shut down or saturated
         * and so reject the task.
         */
        int c = ctl.get();
        if (workerCountOf(c) < corePoolSize) {
            //worker 개수가 coreSize보다 작을 때
            if (addWorker(command, true))
                //worker를 새로 만들어 command를 실행한다. 성공하면 바로 return;
                return;
            c = ctl.get();
        }
        if (isRunning(c) && workQueue.offer(command)) {
            // (worker 개수가 coreSize보다 worker가 크고)
            // executor가 Running 상태이며
            // workQueue에 command가 잘 들어갔을 때 = 큐가 꽉차지 않았단 얘기
            int recheck = ctl.get();
            if (! isRunning(recheck) && remove(command))
                // executor가 Running 상태가 아니면서,
                // command가 workQueue에서 remove 되었을 때 (Queue에 없었단 얘기는 이미 큐에서 빠져나가 실행되고 있었단 얘기겠지)
                reject(command);
            else if (workerCountOf(recheck) == 0)
                //workerCount가 0이면 실행되고 있는 worker가 없다는 얘기이므로, worker를 추가해놓는다.
                //근데 coreSize보다 클 때 왔는데 이 if문에 걸리는 경우가 흔할까?
                addWorker(null, false);
        }
        else if (!addWorker(command, false))
            // (worker 개수가 coreSize보다 worker가 크고)
            // (executor가 Running상태가 아니거나 workQueue가 꽉 차 있었을 때)
            // worker를 추가
            //- 근데 addWorker에서 false면 command가 아닌 workQueue의 head를 빼고, command를 넣어야 하는 로직이 필요할 거 같은데 그게 왜 없을까?
            // 이 로직은 Work 안에서 구현되어 있었다! 얘가 블록킹 큐로 큐를 바라보면서 큐에 task가 있을 때 바로 실행을 한다....!
            reject(command);
    }

```